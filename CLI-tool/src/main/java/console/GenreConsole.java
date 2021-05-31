package console;

import crud.CrudGenre;
import interfaces.IConsole;
import models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class GenreConsole implements IConsole<Genre> {

    private Scanner scanner;
    private EntityManagerFactory emf;
    private EntityManager em;

    public GenreConsole(Scanner scanner, EntityManagerFactory emf, EntityManager em) {
        this.scanner = scanner;
        this.emf = emf;
        this.em = em;
    }

    CrudGenre crudGenre = new CrudGenre();

    public void genreMenu() {
        System.out.println("Enter Genre Name: ");
        String genreName = scanner.next();
        findByName(genreName);

        System.out.println("1: Add Genre");
        System.out.println("2: Delete Genre");
        System.out.println("3: Get Genres");
        System.out.println("4: Find Genre");
        System.out.println("5: Update Genre");

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
        System.out.println("Enter Genre Name: ");
        String name = scanner.next();
        Genre genre = new Genre();
        genre.setName(name);
        crudGenre.add(genre, em);
    }

    public void delete() {
        System.out.println("Enter Genre ID: ");
        long id = scanner.nextInt();
        Genre genre = em.find(Genre.class, id);
        crudGenre.delete(genre, em);
    }

    public void update() {
        System.out.println("Enter Genre ID: ");
        long id = scanner.nextInt();
        em.getTransaction().begin();
        Genre genre = em.find(Genre.class, id);
        crudGenre.update(genre, em, scanner);
    }

    public List<Genre> getList() {
        return crudGenre.getList(em);
    }

    public List<Genre> findByName(String name) {
        return crudGenre.findByName(name, em);
    }

}


