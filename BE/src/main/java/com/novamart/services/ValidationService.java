package com.novamart.services;

import com.novamart.dtos.user.UserDetailsDto;

public interface ValidationService {
	void validateUser(UserDetailsDto userDetailsDto);
}