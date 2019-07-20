package db;

import com.google.protobuf.AbstractMessageLite;
import model.BlogPostModel;
import model.UserModel;
import protobuf.GuicePlaygroundProtos;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Singleton
public class MockDb implements Database {

    private static final String TARGET_FOLDER = "target/";
    private static final String[] TEST_USERNAMES = {"dawid", "max"};
    private static final String[] TEST_BLOGPOST_TITLES = {"test1", "test2"};

    private final Logger logger;
    private boolean logQueries;

    @Inject
    MockDb(final Logger logger) {
        this.logger = logger;
        init();
    }

    private void init() {
        final Random random = new Random();
        Arrays.asList(TEST_USERNAMES).forEach(username -> saveUser(username, random.nextInt(99)));
        Arrays.asList(TEST_BLOGPOST_TITLES).forEach(title -> saveBlogPost(title, Integer.toString(random.nextInt(999999))));
    }

    private void saveUser(final String username, final int age) {
        final GuicePlaygroundProtos.User user = GuicePlaygroundProtos.User.newBuilder().setUsername(username).setAge(age).build();
        saveProtobuf(user, username);
    }

    private void saveBlogPost(final String title, final String body) {
        final GuicePlaygroundProtos.BlogPost blogPost = GuicePlaygroundProtos.BlogPost.newBuilder().setTitle(title).setBody(body).build();
        saveProtobuf(blogPost, title);
    }

    private void saveProtobuf(final AbstractMessageLite protobuf, final String filePrefix) {
        try (final FileOutputStream fos = new FileOutputStream(TARGET_FOLDER + filePrefix + ".proto.ser")) {
            protobuf.writeTo(fos);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private GuicePlaygroundProtos.User readUser(final String filePrefix) {
        try (final FileInputStream fos = new FileInputStream(TARGET_FOLDER + filePrefix + ".proto.ser")) {
            return GuicePlaygroundProtos.User.parseFrom(fos);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private GuicePlaygroundProtos.BlogPost readBlogPost(final String filePrefix) {
        try (final FileInputStream fos = new FileInputStream(TARGET_FOLDER + filePrefix + ".proto.ser")) {
            return GuicePlaygroundProtos.BlogPost.parseFrom(fos);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<UserModel> findAllUsers() {
        if (logQueries) {
            logger.info("Querying: " + Arrays.toString(TEST_USERNAMES));
        }
        return Arrays.stream(TEST_USERNAMES)
                .map(this::readUser)
                .map(protobuf -> UserModel.create(protobuf.getUsername(), protobuf.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BlogPostModel> findAllBlogPosts() {
        if (logQueries) {
            logger.info("Querying: " + Arrays.toString(TEST_BLOGPOST_TITLES));
        }
        return Arrays.stream(TEST_BLOGPOST_TITLES)
                .map(this::readBlogPost)
                .map(protobuf -> BlogPostModel.create(protobuf.getTitle(), protobuf.getBody()))
                .collect(Collectors.toList());
    }

    @Override
    public void enableQueryLogging(final boolean enabled) {
        logQueries = enabled;
    }
}
