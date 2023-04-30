package mk.ukim.finki.emt.eshop.model;

import lombok.Data;

import javax.persistence.*;

//За секоја земја се
//чуваат податоците: id (Long), name (String), continent (String).
@Data
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //

    private String name; //

    private String continent; //

    public Country() {
    }

    public Country (Long id, String name, String continent){
        this.id = id;
        this.name = name;
        this.continent = continent;
    }

}
