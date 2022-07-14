package ru.atroshenko.telegrambot.services;

import org.springframework.stereotype.Service;
import ru.atroshenko.telegrambot.entities.Product;
import ru.atroshenko.telegrambot.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductByName(String name) {
        return productRepository.getByName(name);
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.getProductByCategoryId(categoryId);
    }

    public  List<Product> getProductsByClientId(Long clientId) {
        return productRepository.getProductsById(clientId);
    }

    public List<Product> getTopPopularProducts(Integer top) {
        List<Product> products = productRepository.getTopPopularProducts(top);
        return products.stream().limit(top).collect(Collectors.toList());
    }
}
