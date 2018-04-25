package by.megumin.controller;

import by.megumin.dto.AmountDto;
import by.megumin.dto.CartDto;
import by.megumin.dto.DetailDto;
import by.megumin.entity.orderEntity.Cart;
import by.megumin.entity.productEntity.Category;
import by.megumin.entity.productEntity.Product;
import by.megumin.entity.userEntity.User;
import by.megumin.service.CartService;
import by.megumin.service.CategoryService;
import by.megumin.service.ProductService;
import by.megumin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> addCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }

    @ModelAttribute("countProductInCart")
    public Integer getCountProductInCar() {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        return cartService.getCountProductsInCart(user);
    }

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

//    @GetMapping("/cart")
//    public String cart() {
//        return "cart";
//    }

    @GetMapping("/user/cart")
    public String userCart(Model model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        List<Cart> carts = cartService.getByUser(user);
        if(carts.size() == 0) {
            model.addAttribute("empty", true);
        } else {
            model.addAttribute("carts", carts);
        }
        return "cart";
    }

    @GetMapping("/user/cart/clean")
    public String cleanCart() {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        cartService.cleanByUser(user);
        return "redirect:/user/cart";
    }

    @PostMapping("/user/cart/delete")
    @ResponseBody
    public AmountDto cartDelete(@RequestBody CartDto cartDto) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        Product product = productService.getByID(cartDto.getProductId());
        cartService.deleteProductFromCart(user, product, cartDto.getAmount());
        AmountDto amountDto = new AmountDto();
        amountDto.setAmount(cartService.getCountProductsInCart(user));
        return amountDto;
    }

    @PostMapping("/user/cart/add")
    @ResponseBody
    public AmountDto addToCart(@RequestBody CartDto cartDto) {
        System.out.println("ДОБАВЛЕНИЕ В КОРЗИНУ ВЫЗВАНО");
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        Product product = productService.getByID(cartDto.getProductId());
        System.out.println("PRODUCT: " +  product);
        System.out.println("AMOUNT: " + cartDto.getAmount());
        cartService.addToCart(user, product, cartDto.getAmount());
        AmountDto amountDto = new AmountDto();
        amountDto.setAmount(cartService.getCountProductsInCart(user));
        return amountDto;
    }
}