package pro.chukai.web.dto.out;

public class JsonResult<T> {
    private int code;
    private T result;
    private String msg;

    private JsonResult(int code, T result, String msg) {
        this.code = code;
        this.result = result;
        this.msg = msg;
    }

    public static <T> JsonResult<T> ofSuccess(String msg) {
        return ofSuccess(null, msg);
    }

    public static <T> JsonResult<T> ofSuccess(T result) {
        return ofSuccess(result, null);
    }

    public static <T> JsonResult<T> ofSuccess(T result, String msg) {
        return new JsonResult<T>(1, result, msg);
    }

    public static <T> JsonResult<T> ofFailure(String msg) {
        return ofFailure(null, msg);
    }

    public static <T> JsonResult<T> ofFailure(T result, String msg) {
        return new JsonResult<T>(-1, result, msg);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getResult() {
        return result;
    }

}
