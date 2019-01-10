package my.java.vlong.homework3_refactor.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, K> {

    Optional<T> add(T t);

    Optional<T> update(T t);

    boolean delete(Optional<T> t);

    Optional<T> findByOne(K k);

    List<T> findAll();
}
