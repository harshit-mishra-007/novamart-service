package com.novamart.dtos.user;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
	private String id;
	private String username;
	private String email;
	private String password;
	private String role;
	private AddressDto address;
	private LocalDateTime createdAt;
}