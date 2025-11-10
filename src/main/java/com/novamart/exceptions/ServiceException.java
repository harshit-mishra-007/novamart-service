package com.novamart.exceptions;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ServiceException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;
	private final String message;
	private final String productId;
	
	public ServiceException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.productId = null;
	}
}