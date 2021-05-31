package crud;

import helper.ParseDate;
import interfaces.ICrud;
import models.Movie;
import models.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class CrudPerson implements ICrud<Person> {
    @Override
    public void add(Person person, EntityManager em) {
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Person person, EntityManager em) {
        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();
    }

    @Override
    public void update(Person person, EntityManager em, Scanner scanner) {
        System.out.println("1: Update Name | 2: Update LastName | 3: Update Birthday");
        int menu = scanner.nextInt();
        switch (menu) {
            case 1:
                System.out.println("Enter new name: ");
                person.setName(scanner.next());
                break;
            case 2:
                System.out.println("Enter new LastName: ");
                person.setSurname(scanner.next());
                break;
            case 3:
                System.out.println("Enter new Birthday Date: format(yyyy-MM-dd): ");
                try {
                    person.setBirthDate(ParseDate.parseDateFromString(scanner.next()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
        em.getTransaction().commit();
    }

    @Override
    public List<Person> getList(EntityManager em) {
        Query query = em.createNativeQuery("select * from people", Person.class);
        List<Person> people = query.getResultList();

        for (Person person : people) {
            System.out.print("ID: " + person.getId() + " | ");
            System.out.print("Name: " + person.getName() + " | ");
            System.out.print("Surname: " + person.getSurname() + " | ");
            System.out.print("Birthday Date: " + person.getBirthDate() + " | ");
            for (Movie movie : person.getMovies()) {
                System.out.print("Movies: " + movie.getTitle() + " | ");
            }
            System.out.print("Gender: " + person.getGender().getValue());
            System.out.println();
        }
        return people;
    }

    @Override
    public List<Person> findByName(String name, EntityManager em) {
        Query query = em.createQuery(
                "SELECT p FROM Person p WHERE p.name like :name");
        query.setParameter("name", name + "%");
        List<Person> people = query.getResultList();
        for (Person person : people) {
            System.out.print("ID: " + person.getId() + " | ");
            System.out.print("Name: " + person.getName() + " | ");
            System.out.print("Surname: " + person.getSurname() + " | ");
            System.out.print("Birthday Date: " + person.getBirthDate() + " | ");
            for (Movie movie : person.getMovies()) {
                System.out.print("Movies: " + movie.getTitle() + " | ");
            }
            System.out.print("Gender: " + person.getGender().getValue());
            System.out.println();
        }
        return people;
    }
}