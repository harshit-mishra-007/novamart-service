package com.novamart.services.impl;

import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.novamart.dtos.user.UserDetailsDto;
import com.novamart.entities.user.UserDetails;
import com.novamart.exceptions.ServiceException;
import com.novamart.mappers.impl.UserMapper;
import com.novamart.repositories.UsersRepository;
import com.novamart.services.UserService;
import com.novamart.services.ValidationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	private final UsersRepository userRepository;
	private final ValidationService validationService;
	private final UserMapper mapper;
	@Value("${email.not.available}")
	private String emailNotFound;
	
	public UserServiceImpl(UsersRepository userRepository, ValidationService validationService, UserMapper mapper) {
		this.userRepository = userRepository;
		this.validationService = validationService;
		this.mapper = mapper;
	}
	
	@Override
	public UserDetailsDto fetchUserDetailsByEmail(String email) {
		UserDetails userDetails = userRepository.fetchUserDetailsByEmail(email);
		if (!ObjectUtils.isEmpty(userDetails)) {
			log.info("Fetched User with Email {}", email);
			return mapper.toDto(userDetails);
		} else
			throw new ServiceException(HttpStatus.NOT_FOUND, MessageFormat.format(emailNotFound, email));
	}

	@Override
	public List<UserDetailsDto> fetchAllUsers() {
		return mapper.toDtoList(userRepository.findAll());
	}

	@Override
	public UserDetailsDto createOrUpdateUser(UserDetailsDto userDetailsDto) {
		validationService.validateUser(userDetailsDto);
		log.info("Creating new user with username {}", userDetailsDto.getUsername());
		UserDetails userDetails = mapper.toEntity(userDetailsDto);
		UserDetails savedUser = userRepository.save(userDetails);
		return mapper.toDto(savedUser);
	}
}