package by.megumin.controller;

import by.megumin.entity.userEntity.User;
import by.megumin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/login")
    public String getUserInfo(@RequestParam(name = "error", required = false) String error, Model model) {
        if(error != null) {
            model.addAttribute("incorrectData", true);
        }
        return "login";
    }
}