package mk.ukim.finki.emt.eshop.repository.impl;

import mk.ukim.finki.emt.eshop.bootstrap.DataHolder;
import mk.ukim.finki.emt.eshop.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCountryRepository {
    public List<Country> findAll() {
        return DataHolder.countries;
    }

    public Optional<Country> findById(Long id) {
        return DataHolder.countries.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Country> save(Long id, String name, String continent) {
        Country country = new Country(id, name, continent);
        DataHolder.countries.add(country);
        return Optional.of(country);
    }

    public boolean deleteById(Long id) {
        return DataHolder.countries.removeIf(i -> i.getId().equals(id));
    }
}
