package az.edu.ada.wm2.lab4.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import az.edu.ada.wm2.lab4.model.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(UUID id);
    List<Product> getAllProducts();
    Product updateProduct(UUID id, Product product);
    void deleteProduct(UUID id);
    List<Product> getProductsExpiringBefore(LocalDate date);
    List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
}
