package com.novamart.services;

import com.novamart.dtos.product.ProductDetailsDto;

public interface ProductService {
	ProductDetailsDto fetchProductDetails(String productId);
}