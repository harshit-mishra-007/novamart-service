package com.novamart.services.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.novamart.dtos.user.UserDetailsDto;
import com.novamart.entities.user.UserDetails;
import com.novamart.exceptions.ServiceException;
import com.novamart.repositories.UsersRepository;
import com.novamart.services.ValidationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ValidationServiceImpl implements ValidationService {
	
	private final UsersRepository userRepository;
	
	public ValidationServiceImpl(UsersRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void validateUser(UserDetailsDto userDetailsDto) {
		log.info("Checking the user email");
		UserDetails userDetails = userRepository.fetchUserDetailsByEmail(userDetailsDto.getEmail());
		if(!ObjectUtils.isEmpty(userDetails)) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "The email is already existing");
		}
	}
}