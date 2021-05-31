package crud;

import interfaces.ICrud;
import models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class CrudGenre implements ICrud<Genre> {
    @Override
    public void add(Genre genre, EntityManager em) {
        em.getTransaction().begin();
        em.merge(genre);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Genre genre, EntityManager em) {
        em.getTransaction().begin();
        em.remove(genre);
        em.getTransaction().commit();
    }

    @Override
    public void update(Genre genre, EntityManager em, Scanner scanner) {
        System.out.println("Enter new name: ");
        String name = scanner.next();
        genre.setName(name);
        em.getTransaction().commit();
    }

    @Override
    public List<Genre> getList(EntityManager em) {
        Query query = em.createNativeQuery("select * from genres", Genre.class);
        List<Genre> genres = query.getResultList();

        for (Genre genre : genres) {
            System.out.print("ID: " + genre.getId() + " | ");
            System.out.print("Name: " + genre.getName());
            System.out.println();
        }

        return genres;
    }

    @Override
    public List<Genre> findByName(String name, EntityManager em) {
        Query query = em.createQuery(
                "SELECT g FROM Genre g WHERE g.name like :name");
        query.setParameter("name", name + "%");
        List<Genre> genres = query.getResultList();
        for (Genre genre : genres) {
            System.out.print("ID: " + genre.getId() + " | ");
            System.out.print("Name: " + genre.getName());
            System.out.println();
        }
        return genres;
    }
}
