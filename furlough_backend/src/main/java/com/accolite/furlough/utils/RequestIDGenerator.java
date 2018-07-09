package com.accolite.furlough.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class RequestIDGenerator
        implements
        IdentifierGenerator {

    @Override
    public Serializable generate(final SharedSessionContractImplementor session, final Object object)
            throws HibernateException {

        final String prefix = "REQ";
        final Connection connection = session.connection();

        try {
            final Statement statement = connection.createStatement();
            final ResultSet rs = statement.executeQuery("select count(requestID) as ID from furlough.furloughLog");

            if (rs.next()) {
                final int id = rs.getInt(1);
                final String generatedID = prefix + new Integer(id).toString();
                return generatedID;
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
