package mk.ukim.finki.emt.eshop.service.impl;

import mk.ukim.finki.emt.eshop.model.Author;
import mk.ukim.finki.emt.eshop.model.Country;
import mk.ukim.finki.emt.eshop.repository.impl.InMemoryAuthorRepository;
import mk.ukim.finki.emt.eshop.repository.impl.InMemoryCountryRepository;
import mk.ukim.finki.emt.eshop.service.CountryService;

import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {
    private final InMemoryCountryRepository countryRepository;

    public CountryServiceImpl(InMemoryCountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> save(Long id, String name, String continent) {
//        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name, address)));
        return this.countryRepository.save(id, name, continent);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
