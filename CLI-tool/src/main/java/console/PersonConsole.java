package console;

import crud.CrudPerson;
import helper.ParseDate;
import interfaces.IConsole;
import models.Gender;
import models.Movie;
import models.Person;
import models.Profession;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PersonConsole implements IConsole<Person> {

    private Scanner scanner;
    private EntityManagerFactory emf;
    private EntityManager em;

    public PersonConsole(Scanner scanner, EntityManagerFactory emf, EntityManager em) {
        this.scanner = scanner;
        this.emf = emf;
        this.em = em;
    }

    CrudPerson crudPerson = new CrudPerson();

    public void personMenu() throws ParseException {
        System.out.println("Enter Person Name: ");
        String byName = scanner.next();
        findByName(byName);

        System.out.println("1: Add Person");
        System.out.println("2: Delete Person");
        System.out.println("3: Get Person List");
        System.out.println("4: Find Person");
        System.out.println("5: Update Person");

        int menu = scanner.nextInt();
        switch (menu) {
            case 1:
                add();
                break;
            case 2:
                delete();
                break;
            case 3:
                getList();
                break;
            case 4:
                System.out.println("Enter Name: ");
                String name = scanner.next();
                findByName(name);
                break;
            case 5:
                update();
                break;
            default:
                System.out.println("Enter correct menu.");
                break;
        }

    }


    @Override
    public void add() throws ParseException {
        String regex = "(\\d{4})-(\\d{2})-(\\d{2})";

        System.out.println("Enter Name");
        String name = scanner.next();
        System.out.println("Enter Surname");
        String surname = scanner.next();
        System.out.println("Enter BirthDate | yyyy-MM-dd");
        String date = scanner.next();
        Pattern dateRegEx = Pattern.compile(regex);

        System.out.println("Choose gender: 1. Man | 2. Woman");
        int genderSc = scanner.nextInt();
        Gender gender = em.find(Gender.class, genderSc);

        List<Movie> movies = em.createNativeQuery("select * from movies", Movie.class).getResultList();
        for (Movie getMovie : movies) {
            System.out.print("ID: " + getMovie.getId() + " | ");
            System.out.print("Title: " + getMovie.getTitle());
            System.out.println();
        }
        long movieId = scanner.nextLong();
        Movie movie = em.find(Movie.class, movieId);


        List<Profession> professions = em.createNativeQuery("select * from professions", Profession.class).getResultList();

        for (Profession getProfession : professions) {
            System.out.print("ID: " + getProfession.getId() + " | ");
            System.out.print("NAME: " + getProfession.getName());
            System.out.println();
        }


        System.out.println("Enter profession name: ");

        long professionM = scanner.nextLong();
        Profession findProfession = em.find(Profession.class, professionM);

//        Matcher match = dateRegEx.matcher(date);

        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);
        person.setBirthDate(ParseDate.parseDateFromString(date));
        person.setGender(gender);
        person.add(movie);
        person.addProfession(findProfession);

        crudPerson.add(person, em);

    }

    @Override
    public void delete() {
        System.out.println("Enter Person ID: ");
        long id = scanner.nextInt();
        Person person = em.find(Person.class, id);
        crudPerson.delete(person, em);
    }

    @Override
    public void update() {
        System.out.println("Enter Person ID: ");
        long id = scanner.nextInt();
        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        crudPerson.update(person, em, scanner);
    }

    @Override
    public List<Person> getList() {
        return crudPerson.getList(em);
    }

    @Override
    public List<Person> findByName(String name) {
        return crudPerson.findByName(name, em);
    }

}
