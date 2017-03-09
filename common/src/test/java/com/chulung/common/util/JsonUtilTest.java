package com.chulung.common.util;

import static org.assertj.core.api.Assertions.*;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * Created by wenchukai1 on 2017/3/9.
 */
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