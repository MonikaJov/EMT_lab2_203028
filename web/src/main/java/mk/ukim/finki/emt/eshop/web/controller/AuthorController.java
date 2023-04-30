package mk.ukim.finki.emt.eshop.web.controller;

import mk.ukim.finki.emt.eshop.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getCategoryPage(Model model){
        model.addAttribute("manufacturers", this.authorService.findAll());
        model.addAttribute("bodyContent", "manufacturers");
        return "master-template";
    }
}
