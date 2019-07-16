package dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Serializable> {
    List<T> findAll();
}
