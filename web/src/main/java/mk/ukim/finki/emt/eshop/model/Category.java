package mk.ukim.finki.emt.eshop.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
//Категоријата на книгата може да
//биде: NOVEL, THRILER, HISTORY, FANTASY, BIOGRAPHY, CLASSICS, DRAMA.
//
//@Data
//@Entity
//public class Category {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @Column(length = 4000)
//    private String description;
//
//    public Category() {
//    }
//
//    public Category(String name, String description) {
//        this.name = name;
//        this.description = description;
//    }
//
//}

public enum Category  {
    NOVEL, THRILER, HISTORY, FANTASY, BIOGRAPHY, CLASSICS, DRAMA;
}
