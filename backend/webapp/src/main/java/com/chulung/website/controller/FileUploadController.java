package com.chulung.website.controller;

import java.util.*;

import com.chulung.website.exception.FileUploadExcetion;
import com.chulung.website.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chulung.website.session.WebSessionSupport;
import com.github.tobato.fastdfs.domain.StorePath;

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
        responseBody.put("url", "https://static.chulung.com/" + path.getFullPath());
        return success(responseBody);
    }
}
