package pro.chukai.web.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chukai on 2017/3/29.
 */
public class HttpStatusException extends RuntimeException {

    private static final long serialVersionUID = -5604578012991146390L;
    private HttpStatus status;

    private Map<Object, Object> body;

    protected HttpStatusException(HttpStatus status) {
        this.status = status;
    }

    protected HttpStatusException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public static HttpStatusException of(HttpStatus status) {
        return new HttpStatusException(status);
    }

    public static HttpStatusException of(HttpStatus status, String message) {
        return new HttpStatusException(status, message);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Map<Object, Object> getBody() {
        return body;
    }

    public void setBody(Map<Object, Object> body) {
        this.body = body;
    }

    public HttpStatusException addAttribute(Object key, Object value) {
        this.body = this.body == null ? new HashMap<>() : body;
        this.body.put(key, value);
        return this;
    }


}
