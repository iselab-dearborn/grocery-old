package org.iselab.grocery.repository;

import java.util.ArrayList;
import java.util.List;

import org.iselab.grocery.domain.Product;

public class ProductRepository {

    private static List<Product> products = new ArrayList<>();

    public void save(Product product) {
        products.add(product);
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findByName(String name) {

        List<Product> found = new ArrayList<>();

        for (Product products : products) {

            if (products.getName().toLowerCase().contains(name.toLowerCase())) {
                found.add(products);
            }
        }

        return found;
    }
}
