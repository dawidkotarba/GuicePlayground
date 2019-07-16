package dao;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import db.Database;
import db.DbModule;
import db.MockDatabase;
import model.BlogPostModel;
import model.UserModel;

public class DaoModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new DbModule());
    }

    @Provides
    public Dao<UserModel> provideUserDao(final @MockDatabase Database database) {
        return new UserDao(database);
    }

    @Provides
    public Dao<BlogPostModel> provideBlogPostDao(final @MockDatabase Database database) {
        return new BlogPostDao(database);
    }
}
