package com.ogloba.ew.wsrg.mybatis.handler;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@MappedTypes(Collection.class)
public class ObjectArrayTypeHandler implements TypeHandler {
    public ObjectArrayTypeHandler() {
        super();
    }

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        Connection conn = ps.getConnection();
        Connection dconn = conn.unwrap(OracleConnection.class);

        String[][] args = (String[][]) parameter;
        Object[] objArray = new Object[args.length];

        StructDescriptor structDesc1 = StructDescriptor.createDescriptor("STRINGARRAYOBJECT", conn);

        for (int j = 0; j < args.length; j++) {
            STRUCT obj1 = new STRUCT(structDesc1, conn, args[j]);
            objArray[j] = obj1;
        }

        ArrayDescriptor arry = ArrayDescriptor.createDescriptor("STRINGARRAYOBJECT",(OracleConnection) dconn);
        ARRAY array = new ARRAY(arry, (OracleConnection) dconn, objArray);
        ps.setObject(i, array, OracleTypes.ARRAY);
    }

    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        Array array = rs.getArray(columnName);

        return array.getArray();
    }

    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Array array = cs.getArray(columnIndex);

        return array.getArray();
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        Array array = rs.getArray(columnIndex);

        return array.getArray();
    }
}
