package mk.ukim.finki.emt.eshop.service.impl;

import mk.ukim.finki.emt.eshop.model.Author;
import mk.ukim.finki.emt.eshop.model.Country;
import mk.ukim.finki.emt.eshop.repository.impl.InMemoryAuthorRepository;
import mk.ukim.finki.emt.eshop.service.AuthorService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final InMemoryAuthorRepository authorRepository;

    public AuthorServiceImpl(InMemoryAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(Long id, String name, String surname, Country country) {
//        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name, address)));
        return this.authorRepository.save(id, name, surname, country);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
