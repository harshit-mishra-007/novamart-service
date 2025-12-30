package com.novamart.controllers;

import java.util.List;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.novamart.dtos.user.UserDetailsDto;
import com.novamart.services.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "${application.rest.context-path}")
@PropertySource(value = "classpath:messages.properties")
@Validated
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDetailsDto>> fetchAllUserDetails() {
		log.info("Fetching all user details");
		List<UserDetailsDto> userDetailsList = userService.fetchAllUsers();
		return ResponseEntity.ok(userDetailsList);
	}
	
	@GetMapping("/users/{username}")
	public ResponseEntity<UserDetailsDto> fetchUserDetailsByUsername(@PathVariable String username) {
		log.info("Fetching user detail by username {},", username);
		UserDetailsDto response = userService.fetchUserDetailsByUsername(username);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserDetailsDto> createUser(@RequestBody UserDetailsDto userDetailsDto) {
		log.info("Creating new user: {}", userDetailsDto.getUsername());
		UserDetailsDto createdUser = userService.createOrUpdateUser(userDetailsDto);
		return ResponseEntity.ok(createdUser);
	}
	
	@PutMapping("/users/{username}")
	public ResponseEntity<UserDetailsDto> updateUser(@PathVariable String username, @RequestBody UserDetailsDto userDetailsDto) {
		log.info("Updating user with username: {}", username);
		UserDetailsDto updatedUser = userService.createOrUpdateUser(userDetailsDto);
		return ResponseEntity.ok(updatedUser);
	}
}