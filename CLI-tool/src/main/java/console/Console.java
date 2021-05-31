package console;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.util.Scanner;

public class Console {

    EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("az.code");
    EntityManager em = emf.createEntityManager();

    private Scanner scan;

    public Console(Scanner scan) {
        this.scan = scan;
    }

    public void consoleMenu() throws ParseException {

        PersonConsole personConsole = new PersonConsole(scan, emf, em);
        ProfessionConsole professionConsole = new ProfessionConsole(scan, emf, em);
        GenreConsole genreConsole = new GenreConsole(scan, emf, em);
        MovieConsole movieConsole = new MovieConsole(scan, emf, em);

        System.out.println("1: Person");
        System.out.println("2: Movie");
        System.out.println("3: Genre");
        System.out.println("4: Profession");


        int menu = scan.nextInt();
        switch (menu) {
            case 1:
                personConsole.personMenu();
                break;
            case 2:
                movieConsole.movieMenu();
                break;
            case 3:
                genreConsole.genreMenu();
                break;
            case 4:
                professionConsole.professionMenu();
                break;
            default:
                System.out.println("Enter correct menu.");
                break;
        }

    }

}
