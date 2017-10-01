package com.wchukai.common.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wchukai on 2017/7/9.
 */
public class MessageDigestUtilTest {

    @Test
    public void encoderByMd5Test() {
        assertThat(MessageDigestUtil.encoderByMd5("0123456789")).isEqualTo("eB5eJF1ptWaXm4bijSPyxw==");
        assertThat(MessageDigestUtil.encoderByMd5("apple")).isEqualTo("HzhwvidPbEmz4xoMZyiVfw==");
        assertThat(MessageDigestUtil.checkEquals("0123456789", "eB5eJF1ptWaXm4bijSPyxw==")).isTrue();
    }


}