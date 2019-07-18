import com.google.inject.Guice;
import com.google.inject.Injector;
import controller.ControllerModule;
import controller.UserController;
import protobuf.GuicePlaygroundProtos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(final String[] args) {
        final Injector injector = Guice.createInjector(new ControllerModule());
        final UserController instance = injector.getInstance(UserController.class);
        System.out.println(instance.showUsers());
        System.out.println(instance.showBlogPosts());

        protobufSerializationTest();
    }

    private static void protobufSerializationTest() {
        final GuicePlaygroundProtos.User user = GuicePlaygroundProtos.User.newBuilder().setUsername("dawid").setAge(33).build();
        try (final FileOutputStream fos = new FileOutputStream("User.proto.ser")) {
            user.writeTo(fos);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }

        try (final FileInputStream fos = new FileInputStream("User.proto.ser")) {
            final GuicePlaygroundProtos.User userFromFile = GuicePlaygroundProtos.User.parseFrom(fos);
            System.out.println(userFromFile);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
