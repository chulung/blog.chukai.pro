package com.wchukai.common.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class JsonUtilTest {

    @Test
    public void toJsonString() throws Exception {
        assertThat(JsonUtil.toJsonString(TestBean.getTestBean())).isEqualTo("{\"string\":\"asdasd\",\"integer\":111,\"aLong\":111,\"map\":{\"a\":1}}");
    }

    @Test
    public void writeAndReadBytes() throws Exception {
        TestBean testBean = TestBean.getTestBean();
        byte[] bytes = JsonUtil.writeBytes(testBean);
        assertThat(JsonUtil.readValue(bytes, TestBean.class)).isEqualTo(testBean);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        assertThat(JsonUtil.readValue(inputStream, TestBean.class)).isEqualTo(testBean);
    }

}