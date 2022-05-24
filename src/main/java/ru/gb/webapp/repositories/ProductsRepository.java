package ru.gb.webapp.repositories;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ru.gb.webapp.model.*;
@Component
public class ProductsRepository {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        this.productList = new ArrayList<>(Arrays.asList(
                new Product(1L, "Яблоки сезонные", 89),
                new Product(2L, "Батон нарезной", 56),
                new Product(3L, "Сыр костромской", 567),
                new Product(4L, "Свинина лопатка", 340),
                new Product(5L, "Фарш домашний", 299))
        );
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(productList);
        }

    public void save(Product product) {
        productList.add(product);
    }

    public Product findById(Long id) {
        return productList.stream().filter(s -> s.getId().equals(id)).findFirst().get();
    }
}
