package com.chulung.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * Created by chulung on 2017/4/6.
 */
public class LocalDateTimeSeralizerTest {
    @Test
    public void Seralizer() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTimeSerializer serializer = new LocalDateTimeSerializer();
        serializer.serialize(localDateTime, new WriterBasedJsonGenerator(any(IOContext.class), anyInt(), any(ObjectCodec.class), any(Writer.class)) {
            @Override
            public void writeString(String sstr) throws IOException {
                super.writeString(sstr);
            }
        }, null);
    }

}