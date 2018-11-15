package com.ogloba.ew.wsrg.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedJdbcTypes(value = JdbcType.CURSOR)
@MappedTypes(java.util.Map.class)
public class ResultSetTypeHandler extends BaseTypeHandler {
    @Override
    public List<Map<String, Object>> getNullableResult(ResultSet rs, String arg1) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

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
    public List<Map<String, Object>> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        ResultSet rs = (ResultSet) cs.getObject(columnIndex);

        return getNullableResult(rs, null);
    }

    @Override
    public void setNonNullParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType arg3) throws SQLException {
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }
}
