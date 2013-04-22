package com.passbook.core;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

class HstoreUserType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.INTEGER};
    }

    @Override
    public Class returnedClass() {
        return Map.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        Map m = (Map) o;
        Map m1 = (Map) o1;
        return m.equals(m1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o)
            throws HibernateException, SQLException {
        String val = resultSet.getString(strings[0]);
        return HstoreHelper.toMap(val);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor)
            throws HibernateException, SQLException {
        String s = HstoreHelper.toString((Map) o);
        preparedStatement.setObject(i, s, Types.OTHER);
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return new HashMap<String, String>((Map) o);
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}
