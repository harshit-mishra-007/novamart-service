package com.novamart.entities.product;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {
	@Id
	private String id;
	private String productId;
	private String name;
	private String description;
	private BigDecimal price;
	private String category;
	private BigDecimal stock;
	private boolean active;
}