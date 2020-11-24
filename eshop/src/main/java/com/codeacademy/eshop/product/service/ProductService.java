package com.codeacademy.eshop.product.service;

import com.codeacademy.eshop.product.exception.ProductNotFoundException;
import com.codeacademy.eshop.product.model.Product;
import com.codeacademy.eshop.product.repository.JdbcTemplateProductRepository;
import com.codeacademy.eshop.product.repository.ProductRepository;
import com.codeacademy.eshop.util.Translator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * This class is responsible for our business logic
 */
@Service
public class ProductService {

    private JdbcTemplateProductRepository jdbcTemplateProductRepository;
    private ProductRepository productRepository;
    List<Product> products = List.of(new Product(), new Product());

    public ProductService(JdbcTemplateProductRepository jdbcTemplateProductRepository, ProductRepository productRepository) {
        this.jdbcTemplateProductRepository = jdbcTemplateProductRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        // business logic goes here (in this class)!
        String currLang = LocaleContextHolder.getLocale().getDisplayName();

        Product alwaysVisibleProduct = new Product();
        alwaysVisibleProduct.setDescription("Visada matomas produktas");
        alwaysVisibleProduct.setPrice(BigDecimal.valueOf(10000));
        alwaysVisibleProduct.setInStock(99999);
        alwaysVisibleProduct.setName(Translator.getMessage("product.language").concat(currLang));

//        List<Product> products = jdbcTemplateProductRepository.getAll();
        List<Product> products = productRepository.findAll();
        products.add(alwaysVisibleProduct);
        return products;
    }

    public Product getProductById(long id) {

        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    public void addProduct(Product product) {
        jdbcTemplateProductRepository.save(product);
    }

    public void deleteById(long id) {
        jdbcTemplateProductRepository.deleteById(id);
    }

    public void updateProductName(Product productFromModel) {
        jdbcTemplateProductRepository.updateNameById(productFromModel.getName(), productFromModel.getId());
    }
}
