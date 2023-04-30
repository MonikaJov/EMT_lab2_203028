package mk.ukim.finki.emt.eshop.service;

import mk.ukim.finki.emt.eshop.model.Author;
import mk.ukim.finki.emt.eshop.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);

    List<Author> findAll();

    Optional<Author> save(Long id, String name, String surname, Country country);

    void deleteById(Long id);
}
