package mk.ukim.finki.emt.eshop.service;

import mk.ukim.finki.emt.eshop.model.Author;
import mk.ukim.finki.emt.eshop.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    public Optional<Country> findById(Long id);
    public List<Country> findAll();
    public Optional<Country> save(Long id, String name, String continent);
    public void deleteById(Long id);
}
