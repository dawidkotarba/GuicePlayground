package model;

import com.google.auto.value.AutoValue;

import java.io.Serializable;

@AutoValue
public abstract class UserModel implements Serializable {

    public abstract String username();

    public abstract int age();

    public static UserModel create(final String username, final int age) {
        return new AutoValue_UserModel(username, age);
    }
}
