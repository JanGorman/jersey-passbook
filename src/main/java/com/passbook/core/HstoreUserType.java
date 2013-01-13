package com.passbook.core;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HstoreUserType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Class returnedClass() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isMutable() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
