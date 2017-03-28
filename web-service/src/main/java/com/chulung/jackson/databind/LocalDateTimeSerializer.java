package com.chulung.jackson.databind;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 针对java8 的LocalDateTime 进行自定义的序列化
 * 
 * @author chulung
 *
 */
public class LocalDateTimeSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		gen.writeString(value == null ? null : value.toString().replace('T', ' '));
	}

}
