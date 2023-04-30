package mk.ukim.finki.emt.eshop.repository.impl;

import mk.ukim.finki.emt.eshop.bootstrap.DataHolder;
import mk.ukim.finki.emt.eshop.model.Author;
import mk.ukim.finki.emt.eshop.model.Book;
import mk.ukim.finki.emt.eshop.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookRepository {

    public List<Book> findAll() {
        return DataHolder.books;
    }

    public Optional<Book> findById(Long id) {
        return DataHolder.books.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Book> findByName(String name) {
        return DataHolder.books.stream().filter(i -> i.getName().equals(name)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.books.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Book> save(Long id, String name, Integer availableCopies,
                                  Category category, Author author) {
        DataHolder.books.removeIf(i -> i.getName().equals(name));
        Book product = new Book(id, name, availableCopies,  category, author);
        DataHolder.books.add(product);
        return Optional.of(product);
    }
}
