package crud;

import interfaces.ICrud;
import models.Profession;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class CrudProfession implements ICrud<Profession> {

    @Override
    public void add(Profession profession, EntityManager em) {
        em.getTransaction().begin();
        em.merge(profession);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Profession profession, EntityManager em) {
        em.getTransaction().begin();
        em.remove(profession);
        em.getTransaction().commit();
    }

    @Override
    public void update(Profession profession, EntityManager em, Scanner scanner) {
        System.out.println("Enter new name: ");
        String name = scanner.next();
        profession.setName(name);
        em.getTransaction().commit();
    }

    public List<Profession> getList(EntityManager em) {
        Query query = em.createNativeQuery("select * from professions", Profession.class);
        List<Profession> professions = query.getResultList();

        for (Profession profession : professions) {
            System.out.print("ID: " + profession.getId() + " | ");
            System.out.print("Name: " + profession.getName());
            System.out.println();
        }
        return professions;
    }

    public List<Profession> findByName(String name, EntityManager em) {
        Query query = em.createQuery(
                "SELECT p FROM Profession p WHERE p.name like :name");
        query.setParameter("name", name + "%");
        List<Profession> professions = query.getResultList();
        for (Profession profession : professions) {
            System.out.println("ID: " + profession.getId() + " | ");
            System.out.println("NAME: " + profession.getName());
            System.out.println();
        }
        return professions;
    }
}
