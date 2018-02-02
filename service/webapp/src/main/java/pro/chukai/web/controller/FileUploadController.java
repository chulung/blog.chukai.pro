package pro.chukai.web.controller;

import pro.chukai.web.exception.FileUploadExcetion;
import pro.chukai.web.service.FileUploadService;
import pro.chukai.web.session.WebSessionSupport;
import com.github.tobato.fastdfs.domain.StorePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pro.chukai.web.exception.FileUploadExcetion;
import pro.chukai.web.service.FileUploadService;
import pro.chukai.web.session.WebSessionSupport;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/fileUpload")
@RestController
public class FileUploadController extends BaseController {
    @Autowired
    private WebSessionSupport webSessionSupport;
    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity postFile(MultipartHttpServletRequest mRequest) {
        if (!webSessionSupport.islogIn()) {
            throw FileUploadExcetion.of(HttpStatus.FORBIDDEN, "未登录");
        }
        Map<String, MultipartFile> map = mRequest.getFileMap();
        if (map.size() != 1) {
            throw FileUploadExcetion.of(HttpStatus.BAD_REQUEST, "文件个数必须为1个");
        }
        MultipartFile file = map.values().iterator().next();
        StorePath path = fileUploadService.upload(file);
        Map<Object, Object> responseBody = new HashMap();
        responseBody.put("success", 1);
        responseBody.put("url", "https://cdn.chukai.pro/" + path.getFullPath());
        return success(responseBody);
    }
}
