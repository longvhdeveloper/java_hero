package my.java.vlong.homework3_refactor.repository;

import java.util.List;

public interface IRepository<T, K> {

    T add(T t);

    T update(T t);

    boolean delete(T t);

    T findByOne(K k);

    List<T> findAll();
}
