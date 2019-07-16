package db;

import model.BlogPostModel;
import model.UserModel;

import java.util.List;

public interface Database {
    List<UserModel> findAllUsers();

    List<BlogPostModel> findAllBlogPosts();

    void enableQueryLogging(final boolean enabled);
}
