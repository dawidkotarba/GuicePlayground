package dao;

import db.Database;
import db.MockDatabase;
import model.UserModel;

import javax.inject.Inject;
import java.util.List;

public class UserDao implements Dao<UserModel> {

    private final Database database;

    @Inject
    UserDao(final @MockDatabase Database database) {
        this.database = database;
    }

    @Override
    public List<UserModel> findAll() {
        return database.findAllUsers();
    }
}
