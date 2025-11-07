package com.novamart.services.impl;

import org.springframework.stereotype.Service;
import com.novamart.dtos.product.ProductDetailsDto;
import com.novamart.entities.product.ProductDetails;
import com.novamart.mappers.GenericMapper;
import com.novamart.repositories.ProductRepository;
import com.novamart.services.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	private final GenericMapper<ProductDetailsDto, ProductDetails> mapper;
	
	public ProductServiceImpl(ProductRepository productRepository, GenericMapper<ProductDetailsDto, ProductDetails> mapper) {
		this.productRepository = productRepository;
		this.mapper = mapper;
	}

	@Override
	public ProductDetailsDto fetchProductDetails(String productId) {
		log.info("Fetching Product By Id {}", productId);
		ProductDetails productDetails = productRepository.fetchProductDetailsByProductId(productId);
		return mapper.toDto(productDetails);
	}
}