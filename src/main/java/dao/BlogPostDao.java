package dao;

import db.Database;
import db.MockDatabase;
import model.BlogPostModel;

import javax.inject.Inject;
import java.util.List;

public class BlogPostDao implements Dao<BlogPostModel> {

    private final Database database;

    @Inject
    BlogPostDao(final @MockDatabase Database database) {
        this.database = database;
    }

    @Override
    public List<BlogPostModel> findAll() {
        return database.findAllBlogPosts();
    }
}
