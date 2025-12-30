package com.novamart.entities.user;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
	@Id
	private String id;
	private String username;
	private String email;
	private String password;
	private String role;
	private Address address;
	private LocalDateTime createdAt;
}