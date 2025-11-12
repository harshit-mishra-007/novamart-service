package com.novamart.services;

import java.util.List;

import com.novamart.dtos.common.ApiResponse;
import com.novamart.dtos.product.ProductDetailsDto;

public interface ProductService {
	ApiResponse<ProductDetailsDto> fetchProductDetails(String productId);
	List<ProductDetailsDto> fetchAllProducts();
	ProductDetailsDto createProduct(ProductDetailsDto productDto);
	ProductDetailsDto updateProduct(String productId, ProductDetailsDto productDto);
	void deleteProduct(String productId);
}