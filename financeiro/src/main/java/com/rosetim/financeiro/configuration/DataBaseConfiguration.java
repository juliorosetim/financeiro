package com.rosetim.financeiro.configuration;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;

@Configuration
public class DataBaseConfiguration {
    @Bean
    public void initialize() throws SQLException {
        initializeDatabase();
    }

    public void initializeDatabase() throws SQLException {
        String databaseName = "financeiro";

        String jdbcUrl = "jdbc:postgresql://localhost:5432/";
        String username = "postgres";
        String password = "postgres";

        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

        boolean databaseExists = databaseExists(connection, databaseName);

        try (connection) {
            if (!databaseExists) {
                createDatabaseIfNotExists(connection, databaseName);
            }

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/financeiro");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDatabaseIfNotExists(Connection connection, String databaseName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE " + databaseName);
    }

    private boolean databaseExists(Connection connection, String databaseName) throws SQLException {
        String query = "SELECT 1 FROM pg_database WHERE datname = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, databaseName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

}
