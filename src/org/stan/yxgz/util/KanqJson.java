package org.stan.yxgz.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KanqJson {
	private static ObjectMapper mapper;

	public static ObjectMapper getMapper() {
		if(mapper == null){
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	public static String stringify(Object o) throws JsonProcessingException{
		return getMapper().writeValueAsString(o);
	}
	
	public static <T> T parse(String json, Class<T> cls) throws IOException{
		return getMapper().readValue(json, cls);
	}
	
	public static <T> T parse(String json, TypeReference<T> type) throws IOException{
		return getMapper().readValue(json, type);
	}
}
