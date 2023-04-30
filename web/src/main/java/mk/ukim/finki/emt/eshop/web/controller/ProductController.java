package mk.ukim.finki.emt.eshop.web.controller;

import mk.ukim.finki.emt.eshop.model.Author;
import mk.ukim.finki.emt.eshop.model.Book;
import mk.ukim.finki.emt.eshop.service.AuthorService;
import mk.ukim.finki.emt.eshop.service.BookService;
import mk.ukim.finki.emt.eshop.model.Category;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final BookService bookService;
    private final AuthorService authorService;

    public ProductController(BookService bookService,
                             AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.findAll();
        model.addAttribute("products", books);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Author> authors = this.authorService.findAll();
            List<Category> categories = List.of(Category.values());
            model.addAttribute("manufacturers", authors);
            model.addAttribute("categories", categories);
            model.addAttribute("product", book);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public String addProductPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        List<Category> categories = List.of(Category.values());
        model.addAttribute("manufacturers", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long idNew,
            @RequestParam String name,
            @RequestParam Integer quantity,
            @RequestParam String category,
            @RequestParam Long manufacturer
    ) {
        if (id != null) {
            this.bookService.edit(id, name, quantity, Category.valueOf(category), manufacturer);
        } else {
            this.bookService.save(idNew, name, quantity, Category.valueOf(category), manufacturer);
        }
        return "redirect:/products";
    }

    @GetMapping("/rent/{id}")
    public String rentBook(@PathVariable Long id){
        Optional<Book> theBook;
        Integer copies;

        theBook = this.bookService.findById(id);
        copies = theBook.get().getAvailableCopies();

        if(copies>0)
            copies -= 1;

        this.bookService.edit(theBook.get().getId(), theBook.get().getName(), copies,
                theBook.get().getCategory(), theBook.get().getAuthor().getId());


        return "redirect:/products";
    }
    @GetMapping("/mark-as-rented-out/{id}")
    public String markAsRented(@PathVariable Long id, Model model){
        Optional<Book> theBook;
        Integer copies;

        theBook = this.bookService.findById(id);
        copies = 0;

        this.bookService.edit(theBook.get().getId(), theBook.get().getName(), copies,
                theBook.get().getCategory(), theBook.get().getAuthor().getId());


        return "redirect:/products";
    }
}
