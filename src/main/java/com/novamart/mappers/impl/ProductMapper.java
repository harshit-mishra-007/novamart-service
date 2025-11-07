package com.novamart.mappers.impl;

import org.springframework.stereotype.Component;
import com.novamart.dtos.product.ProductDetailsDto;
import com.novamart.entities.product.ProductDetails;
import com.novamart.mappers.GenericMapper;

@Component
public class ProductMapper implements GenericMapper<ProductDetailsDto, ProductDetails>{

	@Override
	public ProductDetailsDto toDto(ProductDetails productDetails) {
		ProductDetailsDto productDetailsDto = new ProductDetailsDto();
		productDetailsDto.setProductId(productDetails.getProductId());
		productDetailsDto.setName(productDetails.getName());
		productDetailsDto.setDescription(productDetails.getDescription());
		productDetailsDto.setPrice(productDetails.getPrice());
		productDetailsDto.setCategory(productDetails.getCategory());
		productDetailsDto.setStock(productDetails.getStock());
		productDetailsDto.setActive(productDetails.isActive());
		return productDetailsDto;
	}

	@Override
	public ProductDetails toEntity(ProductDetailsDto productDetailsDto) {
		ProductDetails productDetails = new ProductDetails();
		productDetails.setProductId(productDetailsDto.getProductId());
		productDetails.setName(productDetailsDto.getName());
		productDetails.setDescription(productDetailsDto.getDescription());
		productDetails.setPrice(productDetailsDto.getPrice());
		productDetails.setCategory(productDetailsDto.getCategory());
		productDetails.setStock(productDetailsDto.getStock());
		productDetails.setActive(productDetailsDto.isActive());
		return productDetails;
	}
}