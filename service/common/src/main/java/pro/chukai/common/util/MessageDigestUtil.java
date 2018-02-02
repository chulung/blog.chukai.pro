package pro.chukai.common.util;

import pro.chukai.common.codec.BASE64Encoder;
import pro.chukai.common.exception.UncheckRuntimeException;

import java.security.MessageDigest;

/**
 * Created by chukai on 2017/7/9.
 */
public class MessageDigestUtil {
    private static MessageDigest md5;

    public static String encoderByMd5(String str) {
        try {
            return new BASE64Encoder().encode(MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            throw new UncheckRuntimeException(e);
        }
    }

    public static boolean checkEquals(String str, String encoderStr) {
        return encoderByMd5(str).equals(encoderStr);
    }
}
