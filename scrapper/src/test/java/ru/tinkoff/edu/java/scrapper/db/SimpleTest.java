package ru.tinkoff.edu.java.scrapper.db;

import org.junit.Test;
import org.postgresql.Driver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest extends IntegrationEnvironment {
    @Test
    public void containerSuccessfullyStarted_driver_isPostgres() {
        assertEquals(Driver.class.getName(), SQL_CONTAINER.getDriverClassName());
    }
}
