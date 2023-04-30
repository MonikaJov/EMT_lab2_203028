package mk.ukim.finki.emt.eshop.web.controller;
import mk.ukim.finki.emt.eshop.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

//    private final CategoryService categoryService;
//
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @GetMapping
    public String getCategoryPage(Model model){
        List<Category> categories = List.of(Category.values());
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "categories");
        return "master-template";
    }
}
