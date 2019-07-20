import com.google.inject.Guice;
import com.google.inject.Injector;
import controller.ControllerModule;
import controller.UserController;

public class Main {

    public static void main(final String[] args) {
        final Injector injector = Guice.createInjector(new ControllerModule());
        final UserController instance = injector.getInstance(UserController.class);
        System.out.println(instance.showUsers());
        System.out.println(instance.showBlogPosts());

    }
}
