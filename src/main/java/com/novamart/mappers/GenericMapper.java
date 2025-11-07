package com.novamart.mappers;

public interface GenericMapper<D,E> {
	D toDto(E entity);
	E toEntity(D dto);
}