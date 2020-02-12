package org.iselab.grocery.controller;

import java.util.Collections;
import java.util.List;

import org.iselab.grocery.domain.Product;
import org.iselab.grocery.repository.ProductRepository;
import org.iselab.grocery.util.SystemUtils;

public class ProductController {

    private ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository();
    }

    public int showMenu() {

        SystemUtils.clearScreen();

        System.out.println("Products");

        System.out.println("  [1] Add");
        System.out.println("  [2] Search by Name");
        System.out.println("  [3] Remove by Id");
        System.out.println("  [4] List All");
        System.out.println("  [9] Back");

        System.out.print("Option: ");

        return SystemUtils.getIntFromKeyboard();
    }

    public void listAll() {

        SystemUtils.clearScreen();
        
        String template = "%-2s %5s %-7s %5s %-5s";

        System.out.println(String.format(template, "Id", "|", "Name", "|", "Amount"));
        System.out.println("-------------------------------");
        
        for (Product p : productRepository.findAll()) {
            System.out.println(String.format(template, p.getId(), "|", p.getName(), "|", p.getAmount()));
        }

        SystemUtils.pressEnterKeyToContinue();
    }

    public void searchByName() {

        SystemUtils.clearScreen();

        System.out.print("Search by Name: ");
        String name = SystemUtils.getStringFromKeyboard();

        List<Product> foundProducts = productRepository.findByName(name);

        for (Product p : foundProducts) {
            System.out.println(p);
        }

        SystemUtils.pressEnterKeyToContinue();
    }

    public void addProduct() {

        SystemUtils.clearScreen();
        System.out.println("Add Product: ");

        Product product = new Product();

        System.out.print("Name: ");
        product.setName(SystemUtils.getStringFromKeyboard());

        System.out.print("Amount: ");
        product.setAmount(SystemUtils.getIntFromKeyboard());

        productRepository.save(product);
    }

    public void start() {

        int option = showMenu();

        while (option != 9) {

            switch (option) {
            case 1:
                addProduct();
                break;
            case 2:
                searchByName();
                break;
            case 4:
                listAll();
                break;
            default:
                break;
            }

            option = showMenu();
        }
    }

}
