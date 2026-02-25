package az.edu.ada.wm2.lab4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import az.edu.ada.wm2.lab4.service.ProductService;
import java.util.*;
import java.time.* ;
import java.math.*;
import az.edu.ada.wm2.lab4.model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(201).body(productService.createProduct(product));
    }

    @GetMapping
    ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody Product product){
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.updateProduct(id ,product));
    }

    @DeleteMapping("/{id}")
    ResponseEntity <Void> deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/expiring")
    ResponseEntity<List<Product>> getProductsExpiringBefore(@RequestParam("date") LocalDate date){
        return ResponseEntity.ok(productService.getProductsExpiringBefore(date));
    }

    @GetMapping("/filter/price")
    ResponseEntity<List<Product>> getProductsByPriceRange(@PathVariable BigDecimal min, @PathVariable BigDecimal max){
        return ResponseEntity.ok(productService.getProductsByPriceRange(min , max));
    }

}

