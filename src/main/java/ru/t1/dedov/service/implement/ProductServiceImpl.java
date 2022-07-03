package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dedov.dto.ProductDto;
import ru.t1.dedov.mapper.ProductMapper;
import ru.t1.dedov.model.repository.ProductRepository;
import ru.t1.dedov.service.interfaces.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).get());
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        productRepository.save(productMapper.toEntity(productDto));
        return productDto;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
