package com.ogloba.ew.wsrg.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.ibatis.type.BooleanTypeHandler;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(Boolean.class)
public class FlexibleBooleanTypeHandler extends BooleanTypeHandler {
    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object obj = rs.getObject(columnIndex);

        return (Boolean) ConvertUtils.convert(obj, Boolean.class);
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object obj = rs.getObject(columnName);

        return (Boolean) ConvertUtils.convert(obj, Boolean.class);
    }

    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object obj = cs.getObject(columnIndex);

        return (Boolean) ConvertUtils.convert(obj, Boolean.class);
    }
}
