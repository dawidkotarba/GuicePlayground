package model;

import com.google.auto.value.AutoValue;

import java.io.Serializable;

@AutoValue
public abstract class BlogPostModel implements Serializable {

    public abstract String title();

    public abstract String body();

    public static BlogPostModel create(final String title, final String body) {
        return new AutoValue_BlogPostModel(title, body);
    }
}
