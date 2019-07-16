package db;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.util.logging.Logger;

public class DbModule extends AbstractModule {

    @Provides
    @MockDatabase
    public Database provideMockDatabase(final Logger logger) {
        final Database mockDb = new MockDb(logger);
        mockDb.enableQueryLogging(true);
        return mockDb;
    }
}
