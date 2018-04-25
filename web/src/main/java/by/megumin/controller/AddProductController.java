package by.megumin.controller;

import by.megumin.dto.DetailDto;
import by.megumin.entity.productEntity.Category;
import by.megumin.entity.productEntity.Characteristic;
import by.megumin.entity.productEntity.Detail;
import by.megumin.entity.productEntity.Product;
import by.megumin.service.CategoryService;
import by.megumin.service.CharacteristicService;
import by.megumin.service.DetailService;
import by.megumin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/add-product")
public class AddProductController {

    private CategoryService categoryService;
    public CharacteristicService characteristicService;
    public DetailService detailService;
    private ProductService productService;
    private ServletContext servletContext;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setCharacteristicService(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @Autowired
    public void setDetailService(DetailService detailService) {
        this.detailService = detailService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @ModelAttribute("categories")
    public void addCategories(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.findAll());
    }

    @GetMapping("/first")
    public String addProductFirst(Model model) {
        model.addAttribute("product", new Product());
        return "add-product-first";
    }

    @PostMapping("/first")
    public String addProductPost(Product product, HttpSession session) {
        session.setAttribute("resultProduct", product);
        return "redirect:/admin/add-product/second";
    }

    @GetMapping("/second")
    public String addProduct(Model model, HttpSession session) {
        Product resultProduct = (Product) session.getAttribute("resultProduct");
        if(resultProduct.getCategory() != null) {
            Category category = resultProduct.getCategory();
            model.addAttribute("details", category.getDetails());
            model.addAttribute("detailDto", new DetailDto());
        }
        return "add-product-second";
    }

    @PostMapping("/second")
    public String addCategory(Category category, Model model, HttpSession session) {
        Category resultCategory = categoryService.getByID(category.getId());
        Product resultProduct = (Product) session.getAttribute("resultProduct");
        resultProduct.setCategory(resultCategory);
        session.setAttribute("resultProduct", resultProduct);
        productService.save(resultProduct);
        List<Detail> details = resultCategory.getDetails();
        model.addAttribute("details", details);
        return "redirect:/admin/add-product/second";
    }

    @PostMapping("/add-details")
    @ResponseBody
    public void addDetails(@RequestBody DetailDto detailDto, HttpSession session) {
        Product resultProduct = (Product) session.getAttribute("resultProduct");
        System.out.println(detailDto);
        Detail detail = detailService.getByName(detailDto.getName());
        Characteristic characteristic = new Characteristic(resultProduct, detail, detailDto.getValue());
        characteristicService.save(characteristic);
    }

    @GetMapping("/third")
    public String index() {
        productService.getNextImageNumber();
        return "add-product-third";
    }

    @PostMapping("/third")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session) {

        String rootPath = servletContext.getRealPath("/");
        String relativePath = File.separator + "resources" + File.separator + "images" + File.separator;

        String fileName = String.valueOf(productService.getNextImageNumber()) + "."
                        + file.getOriginalFilename().split("\\.")[1];

        String resultPath = rootPath + relativePath + fileName;

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(resultPath);
            Files.write(path, bytes);
            Product resultProduct = (Product) session.getAttribute("resultProduct");
            resultProduct.setImage(fileName);
            productService.update(resultProduct);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/main_page";
    }
}