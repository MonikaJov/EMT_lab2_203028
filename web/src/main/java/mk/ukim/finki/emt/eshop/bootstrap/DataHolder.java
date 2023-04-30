package mk.ukim.finki.emt.eshop.bootstrap;

import lombok.Getter;
import mk.ukim.finki.emt.eshop.model.*;
import mk.ukim.finki.emt.eshop.model.enumerations.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<User> users = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    public static List<Country> countries = new ArrayList<>();
    private final PasswordEncoder passwordEncoder;

    public DataHolder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void init() {

        users.add(new User( "user", passwordEncoder.encode("user"),"user","user", Role.ROLE_USER));
        users.add(new User( "admin",passwordEncoder.encode("admin"),"admin","admin",  Role.ROLE_LIBRARIAN));

        Country country = new Country(1L, "Macedonia", "Europe");
        countries.add(country);

        Author author = new Author(1L, "Koco", "Racin", country);
        authors.add(author);
        authors.add(new Author(2L, "Blaze", "Koneski", country));

        books.add(new Book(1L,"Dragoviskite bogomili", 20,  Category.HISTORY, author));
        books.add(new Book(2L, "Bogomilite", 10,  Category.HISTORY, author));
    }
}
