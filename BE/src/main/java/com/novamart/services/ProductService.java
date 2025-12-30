package com.novamart.services;

import java.util.List;
import com.novamart.dtos.product.ProductDetailsDto;

public interface ProductService {
	ProductDetailsDto fetchProductDetails(String productId);
	List<ProductDetailsDto> fetchAllProducts();
	ProductDetailsDto createOrUpdateProduct(ProductDetailsDto productDto);
}