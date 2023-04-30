package mk.ukim.finki.emt.eshop.service;

import mk.ukim.finki.emt.eshop.model.Author;
import mk.ukim.finki.emt.eshop.model.Book;
import mk.ukim.finki.emt.eshop.model.Category;
import mk.ukim.finki.emt.eshop.model.dto.BookDto;;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(Long id, String name, Integer availableCopies, Category category, Long authorId);

    Optional<Book> save(Long id, BookDto bookDto);

    Optional<Book> edit(Long id, String name, Integer availableCopies, Category category, Long authorId);

    Optional<Book> edit(Long id, BookDto booktDto);

    void deleteById(Long id);

    void refreshMaterializedView();
}
