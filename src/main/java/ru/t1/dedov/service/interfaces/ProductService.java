package ru.t1.dedov.service.interfaces;

import ru.t1.dedov.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();

    ProductDto findById(Long id);

    ProductDto save(ProductDto productDto);

    void deleteById(Long id);
}
