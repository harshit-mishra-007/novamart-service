package com.novamart.controllers;

import java.util.List;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novamart.dtos.common.ApiResponse;
import com.novamart.dtos.product.ProductDetailsDto;
import com.novamart.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "${application.rest.context-path}")
@PropertySource(value = "classpath:messages.properties")
@Validated
@Slf4j
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("products")
	public ResponseEntity<List<ProductDetailsDto>> fetchAllProductDetails() {
		log.info("Fetching all product details");
		List<ProductDetailsDto> productDetailsList = productService.fetchAllProducts();
		return ResponseEntity.ok(productDetailsList);
	}
	
	@GetMapping("products/{productId}")
	public ResponseEntity<ApiResponse<ProductDetailsDto>> fetchProductDetailByProductId(@PathVariable String productId) {
		log.info("Fetching product detail by id {},", productId);
		ApiResponse<ProductDetailsDto> response = productService.fetchProductDetails(productId);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("products")
	public ResponseEntity<ProductDetailsDto> createProduct(@RequestBody ProductDetailsDto productDto) {
		log.info("Creating new product: {}", productDto.getProductId());
		ProductDetailsDto createdProduct = productService.createProduct(productDto);
		return ResponseEntity.ok(createdProduct);
	}
	
	@PutMapping("products/{productId}")
	public ResponseEntity<ProductDetailsDto> updateProduct(@PathVariable String productId, @RequestBody ProductDetailsDto productDto) {
		log.info("Updating product with id: {}", productId);
		ProductDetailsDto updatedProduct = productService.updateProduct(productId, productDto);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("products/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
		log.info("Deleting product with id: {}", productId);
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
	}
}