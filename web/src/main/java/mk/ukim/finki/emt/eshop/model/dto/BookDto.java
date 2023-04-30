package mk.ukim.finki.emt.eshop.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.eshop.model.Author;
import mk.ukim.finki.emt.eshop.model.Category;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
public class BookDto {

    private String name;

    private Integer availableCopies;

    private Category category;

    private Author author;

    public BookDto() {
    }

    public BookDto(String name, Integer availableCopies, Category category, Author author) {
        this.name = name;
        this.availableCopies = availableCopies;
        this.category = category;
        this.author = author;
    }
}
