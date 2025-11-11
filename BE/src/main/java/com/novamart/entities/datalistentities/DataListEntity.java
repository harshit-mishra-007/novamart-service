package com.novamart.entities.datalistentities;

import java.util.List;
import lombok.Data;

@Data
public class DataListEntity {
	private String id;
	List<Option> options;
}