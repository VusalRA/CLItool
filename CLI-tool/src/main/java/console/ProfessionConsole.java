package console;

import crud.CrudProfession;
import interfaces.IConsole;
import models.Profession;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class ProfessionConsole implements IConsole<Profession> {

    private Scanner scanner;
    private EntityManagerFactory emf;
    private EntityManager em;

    public ProfessionConsole(Scanner scanner, EntityManagerFactory emf, EntityManager em) {
        this.scanner = scanner;
        this.emf = emf;
        this.em = em;
    }

    CrudProfession crudProfession = new CrudProfession();

    public void professionMenu() {
        System.out.println("Enter Profession Name: ");
        String professionName = scanner.next();
        findByName(professionName);

        System.out.println("1: Add Profession");
        System.out.println("2: Delete Profession");
        System.out.println("3: Get Profession");
        System.out.println("4: Find Profession");
        System.out.println("5: Update Profession");

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
        System.out.println("Enter Profession Name: ");
        String name = scanner.next();
        Profession profession = new Profession();
        profession.setName(name);
        crudProfession.add(profession, em);
    }

    public void delete() {
        System.out.println("Enter Profession ID: ");
        long id = scanner.nextInt();
        Profession profession = em.find(Profession.class, id);
        crudProfession.delete(profession, em);
    }

    public void update() {
        System.out.println("Enter Profession ID: ");
        long id = scanner.nextInt();
        em.getTransaction().begin();
        Profession profession = em.find(Profession.class, id);
        crudProfession.update(profession, em, scanner);
    }

    public List<Profession> getList() {
        return crudProfession.getList(em);
    }

    public List<Profession> findByName(String name) {
        return crudProfession.findByName(name, em);
    }


}


