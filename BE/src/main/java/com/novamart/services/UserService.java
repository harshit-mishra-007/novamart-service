package com.novamart.services;

import java.util.List;
import com.novamart.dtos.user.UserDetailsDto;

public interface UserService {
	UserDetailsDto fetchUserDetailsByUsername(String username);
	List<UserDetailsDto> fetchAllUsers();
	UserDetailsDto createOrUpdateUser(UserDetailsDto userDetailsDto);
}