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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	private final UsersRepository userRepository;
	private final UserMapper mapper;
	@Value("${username.not.available}")
	private String userNameNotFound;
	
	public UserServiceImpl(UsersRepository userRepository, UserMapper mapper) {
		this.userRepository = userRepository;
		this.mapper = mapper;
	}
	
	@Override
	public UserDetailsDto fetchUserDetailsByUsername(String username) {
		UserDetails userDetails = userRepository.fetchUserDetailsByUsername(username);
		if (!ObjectUtils.isEmpty(userDetails)) {
			log.info("Fetched User with Username {}", username);
			return mapper.toDto(userDetails);
		} else
			throw new ServiceException(HttpStatus.NOT_FOUND, MessageFormat.format(userNameNotFound, username));
	}

	@Override
	public List<UserDetailsDto> fetchAllUsers() {
		return mapper.toDtoList(userRepository.findAll());
	}

	@Override
	public UserDetailsDto createOrUpdateUser(UserDetailsDto userDetailsDto) {
		log.info("Creating new user with username {}", userDetailsDto.getUsername());
		UserDetails userDetails = mapper.toEntity(userDetailsDto);
		UserDetails savedUser = userRepository.save(userDetails);
		return mapper.toDto(savedUser);
	}
}