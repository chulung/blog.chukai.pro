package pro.chukai.web.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by chukai on 2017/3/29.
 */
public class FileUploadExcetion extends HttpStatusException {
    private static final long serialVersionUID = -5094149918034920742L;

    protected FileUploadExcetion(HttpStatus status) {
        super(status);
    }

    protected FileUploadExcetion(HttpStatus status, String message) {
        super(status, message);
    }
}
