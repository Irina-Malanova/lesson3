package ru.gb.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.webapp.model.Product;
import ru.gb.webapp.services.ProductsService;

@Controller
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    // GET http://localhost:8080/app/show_all
    @GetMapping(value = "/show_all")
    public String showProductsPage(Model model){

        model.addAttribute("productList", productsService.findAll());
        return "productList";
    }

    // GET http://localhost:8080/app/show/{id}
    @GetMapping(value = "/show/{id}")
    public String showProductPageById(Model model, @PathVariable Long id){
        model.addAttribute("product", productsService.findById(id));
        return "product_info";
    }

    // GET http://localhost:8080/app/create
    @GetMapping(value = "/create")
    public String createProduct(){
        return "create_product";
    }

    // POST http://localhost:8080/app/create
    @PostMapping(value = "/create")
    public String saveProduct(@RequestParam Long id, @RequestParam String title, @RequestParam int cost){
        productsService.save(new Product(id, title, cost));
        return "redirect:/show_all";
    }
}
