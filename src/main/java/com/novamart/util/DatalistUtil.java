package com.novamart.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.util.ObjectUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novamart.entities.datalistentities.DataListEntity;

public class DatalistUtil {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static List<DataListEntity> getDataList() throws IOException {
		try (InputStream inputStream = DatalistUtil.class.getResourceAsStream("/datalist.json")) {
			if(ObjectUtils.isEmpty(inputStream))
				throw new IOException("Resource not found: datalist.json");
		return objectMapper.readValue(inputStream, new TypeReference<List<DataListEntity>>() {});
		}
	}
	
	public static DataListEntity getDataList(String id) throws IOException {
		return getDataList().stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
	}
}