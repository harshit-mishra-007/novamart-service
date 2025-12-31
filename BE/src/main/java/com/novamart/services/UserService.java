package com.novamart.services;

import java.util.List;
import com.novamart.dtos.user.UserDetailsDto;

public interface UserService {
	UserDetailsDto fetchUserDetailsByEmail(String email);
	List<UserDetailsDto> fetchAllUsers();
	UserDetailsDto createOrUpdateUser(UserDetailsDto userDetailsDto);
}