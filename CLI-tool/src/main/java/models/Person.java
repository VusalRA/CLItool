package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "People")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    @Column(name = "birth_date")
    private Date birthDate;
    @ManyToOne
    private Gender gender;
    @ManyToMany
    @JoinTable(
            name = "person_movie",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies = new ArrayList<>();

    public void add(Movie movie) {
        movies.add(movie);
        movie.getPeople().add(this);
    }

    @ManyToMany
    @JoinTable(name = "people_professions",
            joinColumns = @JoinColumn(name = "people_id"),
            inverseJoinColumns = @JoinColumn(name = "professions_id"))
    private List<Profession> professions = new ArrayList<>();

    public void addProfession(Profession profession) {
        professions.add(profession);
        profession.getPerson().add(this);
    }

}
