package crud;

import interfaces.ICrud;
import models.Genre;
import models.Movie;
import models.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class CrudMovie implements ICrud<Movie> {
    @Override
    public void add(Movie movie, EntityManager em) {
        em.getTransaction().begin();
        em.merge(movie);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Movie movie, EntityManager em) {
        em.getTransaction().begin();
        em.remove(movie);
        em.getTransaction().commit();
    }

    @Override
    public void update(Movie movie, EntityManager em, Scanner scanner) {
        System.out.println("Enter new title: ");
        String title = scanner.next();
        movie.setTitle(title);
        em.getTransaction().commit();
    }

    @Override
    public List<Movie> getList(EntityManager em) {
        Query query = em.createNativeQuery("select * from movies", Movie.class);
        List<Movie> movies = query.getResultList();

        for (Movie movie : movies) {
            System.out.print("ID: " + movie.getId() + " | ");
            System.out.print("Title: " + movie.getTitle() + " | ");
            for (Genre genre : movie.getGenres()) {
                System.out.print("Genre Name: " + genre.getName() + " | ");
            }
            for (Person person : movie.getPeople()) {
                System.out.print("Person Name: " + person.getName() + " | ");
                System.out.println();
            }
            System.out.println();
        }
        return movies;
    }

    @Override
    public List<Movie> findByName(String name, EntityManager em) {
        Query query = em.createQuery(
                "SELECT m FROM Movie m WHERE m.title like :title");
        query.setParameter("title", name + "%");
        List<Movie> movies = query.getResultList();
        for (Movie movie : movies) {
            System.out.print("ID: " + movie.getId() + " | ");
            System.out.print("Title: " + movie.getTitle() + " | ");
            for (Genre genre : movie.getGenres()) {
                System.out.print("Genre Name: " + genre.getName() + " | ");
            }
            for (Person person : movie.getPeople()) {
                System.out.print("Person Name: " + person.getName() + " | ");
                System.out.println();
            }
            System.out.println();
        }
        return movies;
    }
}
