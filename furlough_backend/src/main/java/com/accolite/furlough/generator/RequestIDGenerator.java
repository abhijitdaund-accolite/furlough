package com.accolite.furlough.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accolite.furlough.service.FileStorageService;

public class RequestIDGenerator
        implements
        IdentifierGenerator {

    private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);

    @Override
    public Serializable generate(final SharedSessionContractImplementor session, final Object object) {

        final String prefix = "REQ";
        final Connection connection = session.connection();
        final String sqlQuery = "select count(requestID) as ID from furlough_log";
        try (final Statement statement = connection.createStatement();
                final ResultSet rs = statement.executeQuery(sqlQuery);) {

            if (rs.next()) {
                final int id = rs.getInt(1);
                return prefix + Integer.toString(id);
            }
        } catch (final SQLException e) {
            log.error("Sql Exception {}", e.getMessage());
        }
        return null;
    }

}
