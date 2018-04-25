package by.megumin.controller;

import by.megumin.entity.productEntity.Category;
import by.megumin.entity.productEntity.Characteristic;
import by.megumin.entity.productEntity.Product;
import by.megumin.service.CategoryService;
import by.megumin.service.CharacteristicService;
import by.megumin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminChangeProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CharacteristicService characteristicService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> addCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/change-product/{id}")
    public String getProduct(@PathVariable("id") Long productId,
                             @RequestParam(value = "error", required = false) Integer error,
                             Model model) {
        Product product = productService.getByID(productId);
        List<Characteristic> characteristics = characteristicService.getByProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("characteristics", characteristics);
        if(error != null) {
            model.addAttribute("optimisticLock", true);
        }
        return "admin-change-product";
    }

    @PostMapping("/change-product/{id}")
    public String changeProduct(@PathVariable("id") Long productId, Product product, Model model, HttpServletRequest request) {
        Product originalProduct = productService.getByID(productId);
        System.out.println("PRODUCT: " + product);
        product.setCategory(originalProduct.getCategory());
        try{
            productService.update(product);
        } catch (HibernateOptimisticLockingFailureException e) {
            String referer = request.getHeader("Referer");
            return "redirect:" + referer + "?error=1";
        }
        return "redirect:/main_page";
    }
}
