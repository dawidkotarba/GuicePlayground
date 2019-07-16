package model;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String username;
    private int age;

    public UserModel(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }
}
