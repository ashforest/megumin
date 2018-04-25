package by.megumin.controller;

import by.megumin.entity.orderEntity.Order;
import by.megumin.entity.orderEntity.OrderContent;
import by.megumin.entity.productEntity.Category;
import by.megumin.entity.userEntity.Profile;
import by.megumin.entity.userEntity.User;
import by.megumin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminGetUsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private OrderContentService orderContentService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> addCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/get-all")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin-get-all-users";
    }

    @GetMapping("/see-profile/{id}")
    public String seeProfile(@PathVariable("id") Long id, Model model) {
        User user = userService.getByID(id);
        Profile profile = profileService.getByUser(user);
        if(profile != null) {
            model.addAttribute("profile", profile);
        }
        model.addAttribute("user", user);
        return "user-profile";
    }

    @GetMapping("/all-orders/{userId}")
    public String seeOrders(@PathVariable("userId") Long userId, Model model) {
        User user = userService.getByID(userId);
        model.addAttribute("user", user);
        List<Order> orders = orderService.getByUser(user);
        if(orders.isEmpty()){
            model.addAttribute("empty", true);
        } else {
            List<List<OrderContent>> contents = new ArrayList<>();
            for(Order order : orders) {
                contents.add(orderContentService.getByOrder(order));
            }
            model.addAttribute("contents", contents);
            model.addAttribute("orders", orders);
        }
        return "my-orders";
    }
}
