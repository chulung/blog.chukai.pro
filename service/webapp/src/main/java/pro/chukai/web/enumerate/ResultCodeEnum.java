package pro.chukai.web.enumerate;

/**
 * @author chukai
 */
public enum ResultCodeEnum {
    SUCCESS(0, "OK"),
    PARAM_ERROR(101,"参数错误");
    private int code;
    private String msg;

    private ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
