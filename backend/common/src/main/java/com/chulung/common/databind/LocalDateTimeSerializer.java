package com.chulung.common.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 针对java8 的LocalDateTime 进行自定义的序列化
 *
 * @author chulung
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeString(value == null ? null : DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(value));
    }

}
