package mk.ukim.finki.emt.eshop.web.controller;

import mk.ukim.finki.emt.eshop.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.emt.eshop.service.AuthService;
import mk.ukim.finki.emt.eshop.model.enumerations.Role;
import mk.ukim.finki.emt.eshop.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.emt.eshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String role) {
        try{
            this.userService.register(username, password, repeatedPassword, name, surname, Role.valueOf(role));
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
