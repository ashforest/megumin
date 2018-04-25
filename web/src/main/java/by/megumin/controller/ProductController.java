package by.megumin.controller;

import by.megumin.dto.FilterDto;
import by.megumin.entity.otherEntity.Review;
import by.megumin.entity.productEntity.Category;
import by.megumin.entity.productEntity.Characteristic;
import by.megumin.entity.productEntity.Detail;
import by.megumin.entity.productEntity.Product;
import by.megumin.entity.userEntity.User;
import by.megumin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CharacteristicService characteristicService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

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

    @GetMapping("/main_page")
    public String defaultMainPage() {
        return "main_page";
    }

    @GetMapping("/category/{id}")
    public String getProductsInCategory(@PathVariable("id") Long id,
                                        @RequestParam(value = "year", required = false) List<Integer> years,
                                        @RequestParam(value = "yearFrom", required = false) Integer yearFrom,
                                        @RequestParam(value = "yearTo", required = false) Integer yearTo,
                                        @RequestParam(value = "priceFrom", required = false) String priceFrom,
                                        @RequestParam(value = "priceTo", required = false) String priceTo,
                                        @RequestParam(value = "os", required = false) List<String> os,
                                        @RequestParam(value = "producer", required = false) List<String> producers,
                                        @RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNumber,
                                        Model model) {
        Category category = categoryService.getByID(id);
        model.addAttribute("category", category);
        List<Detail> details = category.getDetails();
        model.addAttribute("details", details);

        boolean allParamsUndefined = years == null &&
                yearFrom == null &&
                yearTo == null &&
                priceFrom == null &&
                priceTo == null &&
                os == null &&
                producers == null;

        List<Product> products;

        if(allParamsUndefined) {
            products = productService.getByCategoryName(category.getName(), pageNumber);
            model.addAttribute("totalPage", productService.getTotalPage(category));
            model.addAttribute("currentPage", pageNumber);
        } else {
            FilterDto filterDto = new FilterDto(
                    category,
                    years,
                    yearFrom,
                    yearTo,
                    priceFrom,
                    priceTo,
                    os,
                    producers);
            products = productService.getByFilter(filterDto, pageNumber);
            int totalPage = productService.getTotalPageWithFilter(filterDto);
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("currentPage", pageNumber);
        }
        model.addAttribute("products", products);
        return "main_page";
    }

    @GetMapping("/product_info/{id}")
    public String getProductInfo(@PathVariable("id") Long id,
                                 @RequestParam(value = "review", required = false) boolean isOnlyReview,
                                 Model model) {
        Product product = productService.getByID(id);
        List<Review> reviews = reviewService.getByProduct(product);
        List<Characteristic> characteristics = characteristicService.getByProduct(product);
        if(isOnlyReview){
            model.addAttribute("review", true);
        }
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("characteristics", characteristics);
        return "product_info";
    }
}