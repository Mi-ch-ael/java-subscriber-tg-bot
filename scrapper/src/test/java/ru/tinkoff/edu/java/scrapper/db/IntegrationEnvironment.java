package ru.tinkoff.edu.java.scrapper.db;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.DirectoryResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IntegrationEnvironment {
    static final PostgreSQLContainer SQL_CONTAINER;

    static {
        SQL_CONTAINER = new PostgreSQLContainer();
        SQL_CONTAINER.start();
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager.getConnection(
                    SQL_CONTAINER.getJdbcUrl(), SQL_CONTAINER.getUsername(), SQL_CONTAINER.getPassword()
            );
            Database database = DatabaseFactory
                    .getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Path currentDirPath = new File(".").toPath().toAbsolutePath().normalize();
            Path rootDirPath = currentDirPath;
            while(!rootDirPath.getFileName().equals(Path.of("java-subscriber-tg-bot"))) {
                rootDirPath = rootDirPath.getParent();
            }
            Path changelogPath = rootDirPath
                    .resolve("scrapper")
                    .resolve("src")
                    .resolve("main")
                    .resolve("resources")
                    .resolve("migrations");
            Liquibase liquibase = new Liquibase(
                    "master.xml",
                    new DirectoryResourceAccessor(currentDirPath.relativize(changelogPath)),
                    database
            );
            liquibase.update(new Contexts(), new LabelExpression());
        }
        catch (SQLException | LiquibaseException | FileNotFoundException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}
