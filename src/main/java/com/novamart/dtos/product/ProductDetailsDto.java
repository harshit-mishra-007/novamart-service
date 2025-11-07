package com.novamart.dtos.product;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDto {
	private String id;
	private String productId;
	private String name;
	private String description;
	private BigDecimal price;
	private String category;
	private BigDecimal stock;
	private boolean active;
}