package com.novamart.services.impl;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.novamart.dtos.common.ApiResponse;
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
	public ApiResponse<ProductDetailsDto> fetchProductDetails(String productId) {
		log.info("Fetching Product By Id {}", productId);
		ProductDetails productDetails = productRepository.fetchProductDetailsByProductId(productId);
		try {
			if (!ObjectUtils.isEmpty(productDetails))
				return ApiResponse.success(mapper.toDto(productDetails));
			else
				throw new ServiceException(HttpStatus.NOT_FOUND, MessageFormat.format(productIdNotFound, productId));
		} catch (ServiceException e) {
			return ApiResponse.error(e.getMessage());
		}
	}
	
	@Override
	public List<ProductDetailsDto> fetchAllProducts(){
		return mapper.toDtoList(productRepository.findAll());
	}

	@Override
	public ProductDetailsDto createProduct(ProductDetailsDto productDto) {
		log.info("Creating new product with ID {}", productDto.getProductId());
		ProductDetails productDetails = mapper.toEntity(productDto);
		ProductDetails savedProduct = productRepository.save(productDetails);
		return mapper.toDto(savedProduct);
	}

	@Override
	public ProductDetailsDto updateProduct(String productId, ProductDetailsDto productDto) {
		log.info("Updating product with ID {}", productId);
		ProductDetails existingProduct = productRepository.fetchProductDetailsByProductId(productId);
		
		if (ObjectUtils.isEmpty(existingProduct)) {
			throw new ServiceException(HttpStatus.NOT_FOUND, MessageFormat.format(productIdNotFound, productId));
		}
		
		// Update fields
		existingProduct.setName(productDto.getName());
		existingProduct.setDescription(productDto.getDescription());
		existingProduct.setPrice(productDto.getPrice());
		existingProduct.setCategory(productDto.getCategory());
		existingProduct.setStock(productDto.getStock());
		existingProduct.setActive(productDto.isActive());
		
		ProductDetails updatedProduct = productRepository.save(existingProduct);
		return mapper.toDto(updatedProduct);
	}

	@Override
	public void deleteProduct(String productId) {
		log.info("Deleting product with ID {}", productId);
		ProductDetails productDetails = productRepository.fetchProductDetailsByProductId(productId);
		
		if (ObjectUtils.isEmpty(productDetails)) {
			throw new ServiceException(HttpStatus.NOT_FOUND, MessageFormat.format(productIdNotFound, productId));
		}
		
		productRepository.delete(productDetails);
	}
}