package controller;

import com.google.inject.AbstractModule;
import dao.DaoModule;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new DaoModule());
        bind(Controller.class).to(UserController.class);
    }
}
