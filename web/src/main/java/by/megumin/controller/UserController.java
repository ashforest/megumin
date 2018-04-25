package by.megumin.controller;

import by.megumin.entity.orderEntity.Order;
import by.megumin.entity.orderEntity.OrderContent;
import by.megumin.entity.orderEntity.PaymentDetails;
import by.megumin.entity.orderEntity.PaymentType;
import by.megumin.entity.productEntity.Category;
import by.megumin.entity.userEntity.Profile;
import by.megumin.entity.userEntity.User;
import by.megumin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderContentService orderContentService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProfileService profileService;

    @ModelAttribute("categories")
    public List<Category> addCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("countProductInCart")
    public Integer getCountProductInCar() {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        return cartService.getCountProductsInCart(user);
    }

    @GetMapping("/orders")
    public String addOrders(Model model) {
        return "user-orders";
    }

    @GetMapping("/create-order")
    public String addOrder(@RequestParam Integer term, @RequestParam String plan, Model model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        Profile profile = profileService.getByUser(user);
        if (profile != null) {
            model.addAttribute("profile", profile);
        }
        PaymentType paymentType = PaymentType.valueOf(plan);
        orderService.createOrder(user, new PaymentDetails(paymentType, term));
        return "order-success";
    }

    @GetMapping("/my-orders")
    public String seeOrders(Model model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        List<Order> orders = orderService.getByUser(user);
        if (orders.size() == 0) {
            model.addAttribute("empty", true);
        } else {
            List<List<OrderContent>> contents = new ArrayList<>();
            for (Order order : orders) {
                contents.add(orderContentService.getByOrder(order));
            }
            model.addAttribute("contents", contents);
            model.addAttribute("orders", orders);
        }
        return "my-orders";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        Profile profile = profileService.getByUser(user);
        if (profile != null) {
            model.addAttribute("profile", profile);
        }
        return "user-profile";
    }

    @GetMapping("/create-profile")
    public String createProfile(Model model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        Profile profile = profileService.getByUser(user);
        model.addAttribute("profile", profile != null ? profile : new Profile());
        return "user-create-profile";
    }

    @PostMapping("/create-profile")
    public String addProfile(Profile profile) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        profile.setUser(user);
        Profile currentProfile = profileService.getByUser(user);
        if (currentProfile != null) {
            profile.setId(currentProfile.getId());
            profileService.update(profile);
        } else {
            profileService.save(profile);
        }
        return "redirect:profile";
    }
}