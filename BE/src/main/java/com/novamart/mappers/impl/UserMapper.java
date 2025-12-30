package com.novamart.mappers.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.novamart.dtos.user.AddressDto;
import com.novamart.dtos.user.UserDetailsDto;
import com.novamart.entities.user.Address;
import com.novamart.entities.user.UserDetails;
import com.novamart.mappers.GenericMapper;

@Component
public class UserMapper implements GenericMapper<UserDetailsDto, UserDetails> {

	@Override
	public UserDetailsDto toDto(UserDetails userDetails) {
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		userDetailsDto.setId(userDetails.getId());
		userDetailsDto.setUsername(userDetails.getUsername());
		userDetailsDto.setEmail(userDetails.getEmail());
		userDetailsDto.setPassword(userDetails.getPassword());
		userDetailsDto.setRole(userDetails.getRole());
		userDetailsDto.setAddress(toAddressDto(userDetails.getAddress()));
		userDetailsDto.setCreatedAt(userDetails.getCreatedAt());
		return userDetailsDto;
	}

	@Override
	public UserDetails toEntity(UserDetailsDto userDetailsDto) {
		UserDetails userDetails = new UserDetails();
		userDetails.setId(userDetailsDto.getId());
		userDetails.setUsername(userDetailsDto.getUsername());
		userDetails.setEmail(userDetailsDto.getEmail());
		userDetails.setPassword(userDetailsDto.getPassword());
		userDetails.setRole(userDetailsDto.getRole());
		userDetails.setAddress(toAddress(userDetailsDto.getAddress()));
		userDetails.setCreatedAt(userDetailsDto.getCreatedAt());
		return userDetails;
		
	}
	
	public List<UserDetailsDto> toDtoList(List<UserDetails> userDetailsList){
		List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
		userDetailsList.forEach(user -> {
			userDetailsDtoList.add(toDto(user));
		});
		return userDetailsDtoList;
	}
	
	public List<UserDetails> toEntityList(List<UserDetailsDto> userDetailsDtoList){
		List<UserDetails> userDetailsList = new ArrayList<>();
		userDetailsDtoList.forEach(user -> {
			userDetailsList.add(toEntity(user));
		});
		return userDetailsList;
	}
	
	private AddressDto toAddressDto(Address address) {
		AddressDto addressDto = new AddressDto();
		addressDto.setStreet(address.getStreet());
		addressDto.setCity(address.getCity());
		addressDto.setState(address.getState());
		addressDto.setCountry(address.getCountry());
		addressDto.setZipCode(address.getZipCode());
		return addressDto;
	}
	
	private Address toAddress(AddressDto addressDto) {
		Address address = new Address();
		address.setStreet(addressDto.getStreet());
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address.setCountry(addressDto.getCountry());
		address.setZipCode(addressDto.getZipCode());
		return address;
	}
}