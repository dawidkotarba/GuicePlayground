package db;

import model.BlogPostModel;
import model.UserModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Singleton
public class MockDb implements Database {

    private final List<UserModel> USERS = new ArrayList<>();
    private final List<BlogPostModel> BLOG_POSTS = new ArrayList<>();

    private final Logger logger;
    private boolean logQueries;

    @Inject
    MockDb(final Logger logger) {
        this.logger = logger;
        init();
    }

    private void init() {
        USERS.add(new UserModel("dawid", 33));
        USERS.add(new UserModel("max", 34));

        BLOG_POSTS.add(new BlogPostModel("test1", "test1body"));
        BLOG_POSTS.add(new BlogPostModel("test2", "test2body"));
    }

    @Override
    public List<UserModel> findAllUsers() {
        if (logQueries) {
            logger.info("Querying: " + USERS);
        }
        return USERS;
    }

    @Override
    public List<BlogPostModel> findAllBlogPosts() {
        if (logQueries) {
            logger.info("Querying: " + BLOG_POSTS);
        }
        return BLOG_POSTS;
    }

    @Override
    public void enableQueryLogging(final boolean enabled) {
        logQueries = enabled;
    }
}
