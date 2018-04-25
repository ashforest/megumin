package by.megumin.controller;

import by.megumin.entity.userEntity.Role;
import by.megumin.entity.userEntity.User;
import by.megumin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckInController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping(path = "/checkIn")
    public String checkIn() {
        return "checkIn";
    }

    @PostMapping(path = "/checkIn")
    public String saveUser(User user, Model model) {
        user.setRole(Role.USER);
        if(userService.getByLogin(user.getLogin()) != null) {
            model.addAttribute("repeatableLogin", true);
            return "checkIn";
        }
        userService.save(user);
        return "redirect:login";
    }
}