package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.controller.urls.Urls;
import ru.t1.dedov.dto.ProductDto;
import ru.t1.dedov.service.interfaces.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(Urls.Product.FULL)
    public ResponseEntity<String> saveOrUpdate(@RequestBody ProductDto productDto) {
        productService.save(productDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(Urls.Product.FULL)
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping(Urls.Product.Id.FULL)
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping(Urls.Product.Id.FULL)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
