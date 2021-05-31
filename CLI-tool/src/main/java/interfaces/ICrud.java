package interfaces;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public interface ICrud<T> {

    void add(T t, EntityManager em);

    void delete(T t, EntityManager em);

    void update(T t, EntityManager em, Scanner scanner);

    List<T> getList(EntityManager em);

    List<T> findByName(String name, EntityManager em);

}
