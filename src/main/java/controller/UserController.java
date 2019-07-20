package controller;

import dao.Dao;
import model.BlogPostModel;
import model.UserModel;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserController implements Controller {

    private final Dao<UserModel> userDao;
    private final Dao<BlogPostModel> blogPostDao;

    @Inject
    UserController(final Dao<UserModel> userDao, final Dao<BlogPostModel> blogPostDao) {
        this.userDao = userDao;
        this.blogPostDao = blogPostDao;
    }

    public List<String> showUsers() {
        final List<UserModel> users = userDao.findAll();
        return users.stream()
                .map(user -> user.username() + " has age: " + user.age() + "\n")
                .collect(Collectors.toList());
    }

    public List<String> showBlogPosts() {
        final List<BlogPostModel> blogPosts = blogPostDao.findAll();
        return blogPosts.stream()
                .map(post -> post.title() + ", body: " + post.body() + "\n")
                .collect(Collectors.toList());
    }

}
