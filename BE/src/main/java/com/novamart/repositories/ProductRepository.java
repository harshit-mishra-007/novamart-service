package com.novamart.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.novamart.entities.product.ProductDetails;

public interface ProductRepository extends MongoRepository<ProductDetails, String>{
	@Query(value = "{'productId': ?0}")
	ProductDetails fetchProductDetailsByProductId(String productId);
}