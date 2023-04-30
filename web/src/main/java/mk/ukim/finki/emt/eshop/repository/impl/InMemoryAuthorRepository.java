package mk.ukim.finki.emt.eshop.repository.impl;

import mk.ukim.finki.emt.eshop.bootstrap.DataHolder;
import mk.ukim.finki.emt.eshop.model.Author;
import mk.ukim.finki.emt.eshop.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository {

    public List<Author> findAll() {
        return DataHolder.authors;
    }

    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Author> save(Long id, String name, String surname, Country country) {
        Author author = new Author(id, name, surname, country);
        DataHolder.authors.add(author);
        return Optional.of(author);
    }

    public boolean deleteById(Long id) {
        return DataHolder.authors.removeIf(i -> i.getId().equals(id));
    }
}
