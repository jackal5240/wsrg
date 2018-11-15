package com.ogloba.ew.wsrg.mybatis.handler;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class ArrayTypeHandler implements TypeHandler {
    public ArrayTypeHandler() {
        super();
    }

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        Connection conn = ps.getConnection();
        Connection dconn = conn.unwrap(OracleConnection.class);

        ArrayDescriptor arry = ArrayDescriptor.createDescriptor("STRINGARRAY",(OracleConnection) dconn);
        ARRAY array = new ARRAY(arry, (OracleConnection) dconn, parameter);
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
