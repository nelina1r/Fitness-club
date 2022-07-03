package ru.t1.dedov.mapper;

import org.mapstruct.Mapper;
import ru.t1.dedov.dto.ProductDto;
import ru.t1.dedov.model.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product entity);

    Product toEntity(ProductDto dto);
}
