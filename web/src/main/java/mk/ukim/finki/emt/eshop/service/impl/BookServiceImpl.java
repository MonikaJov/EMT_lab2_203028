package mk.ukim.finki.emt.eshop.service.impl;

import mk.ukim.finki.emt.eshop.model.Author;
import mk.ukim.finki.emt.eshop.model.Book;
import mk.ukim.finki.emt.eshop.model.dto.BookDto;
import mk.ukim.finki.emt.eshop.repository.impl.InMemoryAuthorRepository;
import mk.ukim.finki.emt.eshop.repository.impl.InMemoryBookRepository;
import mk.ukim.finki.emt.eshop.model.Category;
import mk.ukim.finki.emt.eshop.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.eshop.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.emt.eshop.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final InMemoryBookRepository bookRepository;
    private final InMemoryAuthorRepository authorRepository;

    public BookServiceImpl(InMemoryBookRepository bookRepository,
                              InMemoryAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Book> save(Long id, String name, Integer availableCopies, Category category, Long authorId) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new ManufacturerNotFoundException(authorId));

        Optional<Book> oldBook = this.bookRepository.findByName(name);
        oldBook.ifPresent(book -> this.bookRepository.deleteById(book.getId()));

        return this.bookRepository.save(id, name, availableCopies, category, author);
    }
    
    @Override
    public Optional<Book> save(Long id, BookDto bookDto) {

        Optional<Book> oldBook = this.bookRepository.findByName(bookDto.getName());
        oldBook.ifPresent(book -> this.bookRepository.deleteById(book.getId()));
        return this.bookRepository.save(id, bookDto.getName(), bookDto.getAvailableCopies(), bookDto.getCategory(), bookDto.getAuthor());
    }
    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Integer availableCopies, Category category,  Long authorId) {

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        book.setName(name);
        book.setAvailableCopies(availableCopies);
        book.setCategory(category);

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new ManufacturerNotFoundException(authorId));
        book.setAuthor(author);

        this.bookRepository.save(id, name, availableCopies, category, author);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        book.setName(bookDto.getName());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        
        book.setCategory(bookDto.getCategory());

        Author author = bookDto.getAuthor();
        book.setAuthor(author);

        this.bookRepository.save(id, bookDto.getName(), bookDto.getAvailableCopies(), bookDto.getCategory(), author);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {
    }
}
