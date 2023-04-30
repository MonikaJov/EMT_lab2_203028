package mk.ukim.finki.emt.eshop.web.rest;

import mk.ukim.finki.emt.eshop.model.Book;
import mk.ukim.finki.emt.eshop.model.Category;

import mk.ukim.finki.emt.eshop.model.dto.BookDto;

import mk.ukim.finki.emt.eshop.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class ProductRestController {

    private final BookService productService;

    public ProductRestController(BookService productService) {
        this.productService = productService;
    }

    @GetMapping
    private List<Book> findAll() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.productService.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long idNew,
            @RequestParam String name,
            @RequestParam Integer quantity,
            @RequestParam String category,
            @RequestParam Long manufacturer
    ) {
        if (id != null) {
            return this.productService.edit(id, name, quantity, Category.valueOf(category), manufacturer)
                    .map(product -> ResponseEntity.ok().body(product))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } else {
            return this.productService.save(idNew, name, quantity, Category.valueOf(category), manufacturer)
                    .map(product -> ResponseEntity.ok().body(product))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDto productDto) {
        return this.productService.edit(id, productDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.productService.deleteById(id);
        if(this.productService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
