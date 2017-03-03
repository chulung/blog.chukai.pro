package com.chulung.common.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by chukai on 2017/2/18.
 */
public class NumberUtilTest {
    @Test
    public void normalization() throws Exception {
        double[] time = {0, 12, 24},
                click = {400, 200, 100},
                comment = {0, 5, 10},
                type = {1, 3, 2};
        NumberUtil.normalization(time);
        NumberUtil.normalization(click);
        NumberUtil.normalization(comment);
        NumberUtil.normalization(type);

    }


}