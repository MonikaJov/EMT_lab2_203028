package mk.ukim.finki.emt.eshop.model;

import lombok.Data;
import mk.ukim.finki.emt.eshop.model.enumerations.Role;

import javax.persistence.*;
//Во рамки на апликацијата се чуваат следните податоци за книгите: id (Long), name (String),
//category (enum), author (Author), availableCopies (Integer).
@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //

    private String name; //

//    private Double price;

    private Integer availableCopies; //private Integer quantity;

     @Enumerated(value = EnumType.STRING)
     private Category category; //@ManyToOne private Category category;

    @ManyToOne
    private Author author; //private Manufacturer manufacturer;

    public Book() {
    }

    public Book(Long id, String name, Integer availableCopies, Category category, Author author) {
        this.id = id;
        this.name = name;
        this.availableCopies = availableCopies;
        this.category = category;
        this.author = author;
    }
}
