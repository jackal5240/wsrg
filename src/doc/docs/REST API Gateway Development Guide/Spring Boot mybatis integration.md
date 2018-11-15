# Spring boot mybatis integration

---

## Revision History

|Date      |Version|Author  |Brief|
|----------|------:|--------|-----|
|2018-09-03  |1.0    |Jeff Jian|Initial version|

---

## 前言
公司幾乎所有project皆採用mybatis作為持久層框架,故在此章節介紹spring boot 在使用上將會遇到的問題以及配置.

---

## Gradle & application.yml

build.gradle 加入mybatis依賴

```groovy

compile group: 'org.mybatis.spring.boot', name: 'mybatis-spring-boot-starter', version: '1.3.1'

```

application.yml

```

mybatis:
  type-handlers-package: com.ogloba.solution.product.gateway.config.mybatis
  configuration:
    map-underscore-to-camel-case: true
    jdbc-type-for-null: 'null'
    
```

## Mybatis configuration Description

#### type-handlers-package
當處理特別的type轉換時需要一個handler來處理,
以公司常見的如collection,cursor....
以前的做法是在mybatis-config.xml中配置,並且還需要註記type如下:
```
	<typeHandlers>
        <typeHandler javaType="Map" jdbcType="CURSOR" handler="com.woodoos.ew.proxy.dao.mybatis.ResultSetTypeHandler"/>
        <typeHandler javaType="Collection" jdbcType="ARRAY" handler="com.woodoos.ew.proxy.dao.mybatis.ArrayTypeHandler"/>
    </typeHandlers>

```

現在只需在application設定handler所在的package位置,且在該handler上註解type:

```java
package com.ogloba.solution.product.gateway.config.mybatis;

@MappedJdbcTypes(value = JdbcType.CURSOR)
@MappedTypes(java.util.Map.class)
public class ResultSetTypeHandler extends BaseTypeHandler {

    @Override
    public List<Map<String, Object>> getNullableResult(ResultSet arg0, String arg1) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        ResultSet rs = (ResultSet) cs.getObject(columnIndex);

        if (rs != null && !rs.isClosed()) {

            ResultSetMetaData data = rs.getMetaData();
            int columnCnt = data.getColumnCount();

            while (rs.next()) {
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for (int i = 1; i <= columnCnt; i++) {
                    String colName = data.getColumnName(i);
                    Object colValue = rs.getObject(colName.toLowerCase());
                    rowMap.put(colName, colValue);
                }
                result.add(rowMap);
            }
        }

        return result;
    }

    @Override
    public void setNonNullParameter(PreparedStatement arg0, int arg1,
            Object arg2, JdbcType arg3) {

    }

    public Object getNullableResult(ResultSet rs, int columnIndex) {
        return null;
    }

}
```

---

#### jdbc-type-for-null

這是為了避免空值作為參數導致丟出expception..這邊就不多做說明了.

---

#### map-underscore-to-camel-case
駝峰命名..這邊以簡單的例子來為大家說明.

以下是一個簡單的語法, 撈出一個產品中的資料..大家應該都知道公司之前幾乎是使用MAP來作為回傳的對象, 在使用上需要一直對KEY NAME去操作,雖然是直接使用 table column name 但使用上還是有些麻煩, 現在使用物件作為回傳對象.

```java

package com.ogloba.solution.product.gateway.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ogloba.solution.product.gateway.demo.entity.Product;

@Mapper
public interface DemoDao {
	
	@Select("SELECT * FROM KO_TB_PRODUCT WHERE ROWNUM = 1")
	public Product getProduct();
}

```

Product class

```java
package com.ogloba.solution.product.gateway.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	
	private Integer seqno;
	private String eanCode;
	private Integer deptSeqno;
	private Integer subCatagorySeqno;
	private String productType;
	private String photo1;
    ..........
```

那麼問題來了mybatis要怎麼知道撈出來得資料欄位該對應到物件中哪個field呢?


* 做法1: <br/>
  @Results mybatis 有提供註解讓你使用, 範例如下 : <br/>
  沒錯就是告訴mybatis 你的field對應到哪個欄位....我並不是很推薦這種做法..原本就已經要將sql  語法寫在該method上,現在又要把對應的欄位跟field 對應起來,如果欄位一多呢...我看到這個dao應該就會...
  
```java
	@Results({
		@Result(property = "seqno", column = "SEQNO"),
		@Result(property = "eanCode", column = "EAN_CODE"),
		@Result(property = "deptSeqno", column = "DEPT_SEQNO"),
		@Result(property = "subCatagorySeqno", column = "SUB_CATAGORY_SEQNO")
        .....................
	})
	@Select("SELECT * FROM KO_TB_PRODUCT WHERE ROWNUM = 1")
	public Product getProduct();
```


* 作法2: <br/>
  就是使用我們的參數 **map-underscore-to-camel-case**, mybatis會自動幫你對應物件中的field與table的column,但是必須符合駝峰命名 mybatis就會幫你自動處理好~相當的方便,這樣就可以省下那一堆的Results的註解..

|Column name|Java field|
|-----------|----------|
|TEST_DEMO  | testDemo |
|OGLOBA_GREAT_TEAM|oglobaGreatTeam|

**問題總是會接踵而來...** <br/>
這時你會想,wow~這樣太方便了吧~我來準備寫我的class....
沒錯我們來打開Toad看如何依照欄位名稱的底線來駝峰命名,當我滿懷熱血的寫到第三個field......恩....沒錯一想到我要花多少時間這樣比對並且還深怕打錯字......<br/>

**身為程式人我們就用程式解決它!**

#### Mybatis tool
在GW中我寫了一個工具 MybatisResultsGenerator : 

```java
/**
 * 此工具可用來產生Mybatis @Results 的annotation,將需要轉換的class位置填入CLASS_PATH.
 * 也可輸入sql語法來產生相對應的class field, 方便開發時使用駝峰命名建立class field.
 *
 * @author jeff
 *
 */
public class MybatisResultsGenerator {
	
	private static String URL = "jdbc:oracle:thin:@192.168.1.27:1521:ORCL";
	private static String USER_NAME = "largo_test";
	private static String PASSWORD = "largo_test";
	private static String SQL = "SELECT * FROM KO_TB_PRODUCT WHERE ROWNUM = 1";
	private static String CLASS_PATH = "com.ogloba.solution.product.gateway.demo.entity.Product";
	
	public static void main(String [] args) {
		Connection connect = null;
		PreparedStatement ps = null;
	    ResultSet resultSet = null;
	    StringBuilder stringBuilder = new StringBuilder();
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			connect = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			ps = connect.prepareStatement(SQL);
			
			resultSet = ps.executeQuery();
			ResultSetMetaData rsm = resultSet.getMetaData();
			int columns = rsm.getColumnCount();
			
		    for (int i = 1; i <= columns; i++) {
		    	
		    	String columnName = rsm.getColumnName(i);
		    	String dataType = rsm.getColumnTypeName(i);
		    	int scale = rsm.getScale(i);
		    	dataType = dataType.equals("NUMBER") ? scale > 0 ? dataType : "INTEGER" : dataType;

		    	stringBuilder.append(columnName + "|" + dataType + ",");
		    }
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 try {
				 if (resultSet != null) resultSet.close();
	             if (ps != null) ps.close();
	             if (connect != null) connect.close();
			 } catch (SQLException e) {
	             e.printStackTrace();
             }
		}
		
		
		
		System.out.println("==========Results annotation===============");
		try {
			//丟入要要產生的entity
			System.out.println(getResultsStr(ClassUtils.getClass(CLASS_PATH)));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//依照DB欄位產出class駝峰命名
		System.out.println("==========Class field===============");
		System.out.println(getClassField(stringBuilder.toString()));

	}
	
	public static String getResultsStr(Class origin) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("@Results({\n");
		
		for (Field field : origin.getDeclaredFields()) {
			String property = field.getName();
			
			String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(property).toUpperCase();
			stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
		}
		
		stringBuilder.append("})");
		
		return stringBuilder.toString();
	}
	
	/**
	 * 對於較複雜的SQL查詢 type轉換還並未完善,因此當使用此方法時請注意java type,
	 * ex : SUM(Column) 這會轉換成Integer, 但也許有可能有小數點因此還是建議改成BigDecimal
	 * 
	 * @param sqlColumn
	 * @return
	 */
	public static String getClassField(String sqlColumn) {
		
		StringBuilder stringBuilder = new StringBuilder();
		String [] sqlArray = sqlColumn.split(",");
		
		for(String column : sqlArray) {
			String [] columns = StringUtils.split(column, "|");
			String filed = transCloumnToField(columns[0]);
			String type = mapperJdbcType(columns[1]);
			
			stringBuilder.append(String.format("private %s %s;\n", type, filed));
		}
		
		return stringBuilder.toString();
	}
	
    public static String transCloumnToField(String column){
        StringBuilder field = new StringBuilder();
        String a[] = column.split("_");
        
        for (String s : a ) {
        	
            if (field.length() == 0 ) {
            	field.append(s.toLowerCase());
            }else{
            	field.append(s.substring(0, 1).toUpperCase());
            	field.append(s.substring(1).toLowerCase());
            }
            
        }
        return field.toString();
    }
    
    public static String mapperJdbcType(String type) {
    	String javaType = null;
    	
    	switch (type) {
		case "INTEGER":
			javaType = "Integer";
			break;
		case "VARCHAR":
			javaType = "String";
			break;
		case "NVARCHAR":
			javaType = "String";
			break;
		case "VARCHAR2":
			javaType = "String";
			break;
		case "CLOB":
			javaType = "String";
			break;
		case "NUMBER":
			javaType = "BigDecimal";
			break;
		case "TIMESTAMP WITH TIME ZONE":
			javaType = "Date";
			break;
		case "NVARCHAR2":
			javaType = "String";
			break;
		case "CHAR":
			javaType = "String";
			break;
		default:
			break;
		}
    	
    	return javaType;
    }
}
```

這樣一來只需要將DB的位置跟語法填入就可以幫你寫好class的field了!
順便也可以處理@Results註解喔!會保留是因為一開始我也是認為這樣寫@Results太慢所以有這個想法,後來發現可以駝峰方便多了但還是保留..以備不時之需. <br/>

![mybatis-class-field](images\mybatis-class-field.png)
