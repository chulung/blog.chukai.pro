package pro.chukai.common.exception;

/**
 * Created by chukai on 2017/7/9.
 */
public class UncheckRuntimeException extends RuntimeException {
    public UncheckRuntimeException(Exception e) {
        super(e);
    }
}
