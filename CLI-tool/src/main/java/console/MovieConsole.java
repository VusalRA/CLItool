package console;

import crud.CrudMovie;
import interfaces.IConsole;
import models.Genre;
import models.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class MovieConsole implements IConsole<Movie> {
    private Scanner scanner;
    private EntityManagerFactory emf;
    private EntityManager em;

    public MovieConsole(Scanner scanner, EntityManagerFactory emf, EntityManager em) {
        this.scanner = scanner;
        this.emf = emf;
        this.em = em;
    }

    CrudMovie crudMovie = new CrudMovie();


    public void movieMenu() {
        System.out.println("Enter Movie Name: ");
        String movieName = scanner.next();
        findByName(movieName);


        System.out.println("1: Add Movie");
        System.out.println("2: Delete Movie");
        System.out.println("3: Get Movies");
        System.out.println("4: Find Movie");
        System.out.println("5: Update Movie");

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

    public void add() {
        System.out.println("Enter Movie Name: ");
        String title = scanner.next();

        List<Genre> genres = em.createNativeQuery("select * from genres", Genre.class).getResultList();

        for (Genre genre : genres) {
            System.out.print("ID: " + genre.getId() + " | ");
            System.out.print("Name: " + genre.getName());
            System.out.println();
        }
        System.out.println("Enter Genre ID: ");
        Long genreId = scanner.nextLong();
        Genre getGenre = em.find(Genre.class, genreId);

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.addGenre(getGenre);

        crudMovie.add(movie, em);

    }

    public void delete() {
        System.out.println("Enter Movie ID: ");
        long id = scanner.nextInt();
        Movie movie = em.find(Movie.class, id);
        crudMovie.delete(movie, em);
    }

    public void update() {
        System.out.println("Enter Movie ID: ");
        long id = scanner.nextInt();
        em.getTransaction().begin();
        Movie movie = em.find(Movie.class, id);
        crudMovie.update(movie, em, scanner);
    }

    public List<Movie> getList() {
        return crudMovie.getList(em);
    }

    public List<Movie> findByName(String title) {
        return crudMovie.findByName(title, em);
    }


}
