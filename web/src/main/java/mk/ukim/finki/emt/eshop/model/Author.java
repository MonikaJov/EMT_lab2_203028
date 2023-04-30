package mk.ukim.finki.emt.eshop.model;

import lombok.Data;

import javax.persistence.*;
// За секој автор пак се
//чуваат податоците: id (Long), name (String), surname (String), country (Country).
@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //

    private String name; //

    private String surname; //@Column(name = "manufacturer_address")  private String address; //private String surname

    @ManyToOne
    private Country country;

    public Author() {
    }

    public Author(Long id, String name, String surname, Country country) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
