package com.novamart.services.impl;

import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.novamart.dtos.product.ProductDetailsDto;
import com.novamart.entities.product.ProductDetails;
import com.novamart.exceptions.ServiceException;
import com.novamart.mappers.impl.ProductMapper;
import com.novamart.repositories.ProductRepository;
import com.novamart.services.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	private final ProductMapper mapper;
	@Value("${product.id.not.available}")
	private String productIdNotFound;
	
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper mapper) {
		this.productRepository = productRepository;
		this.mapper = mapper;
	}

	@Override
	public ProductDetailsDto fetchProductDetails(String productId) {
		ProductDetails productDetails = productRepository.fetchProductDetailsByProductId(productId);
		if (!ObjectUtils.isEmpty(productDetails)) {
			log.info("Fetched Product details By Id {}", productId);
			return mapper.toDto(productDetails);
		} else
			throw new ServiceException(HttpStatus.NOT_FOUND, MessageFormat.format(productIdNotFound, productId));
	}
	
	@Override
	public List<ProductDetailsDto> fetchAllProducts(){
		return mapper.toDtoList(productRepository.findAll());
	}

	@Override
	public ProductDetailsDto createOrUpdateProduct(ProductDetailsDto productDto) {
		log.info("Creating new product with ID {}", productDto.getProductId());
		ProductDetails productDetails = mapper.toEntity(productDto);
		ProductDetails savedProduct = productRepository.save(productDetails);
		return mapper.toDto(savedProduct);
	}
}