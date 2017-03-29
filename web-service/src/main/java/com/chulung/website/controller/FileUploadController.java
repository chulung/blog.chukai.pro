package com.chulung.website.controller;

import java.util.*;

import com.chulung.website.exception.FileUploadExcetion;
import com.chulung.website.exception.HttpStatusException;
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
import com.chulung.common.util.DateUtils;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

@RequestMapping("/api/fileUpload")
@RestController
public class FileUploadController extends BaseController {
    @Autowired
    private WebSessionSupport webSessionSupport;
    @Autowired
    protected FastFileStorageClient storageClient;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity postFile(MultipartHttpServletRequest mRequest) {
        if (!webSessionSupport.islogIn()) {
            throw FileUploadExcetion.of(HttpStatus.FORBIDDEN,"未登录");
        }
        Map<String, MultipartFile> map = mRequest.getFileMap();
        if (map.size() != 1) {
            throw FileUploadExcetion.of(HttpStatus.BAD_REQUEST, "文件个数必须为1个");
        }
        MultipartFile file = map.values().iterator().next();
        String fileName = file.getOriginalFilename();
        Set<MateData> metaDataSet = new HashSet<MateData>();
        metaDataSet.add(new MateData("creator", "system"));
        metaDataSet.add(new MateData("createDate", DateUtils.format(new Date())));
        try {
            StorePath path = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
                    fileName.substring(fileName.lastIndexOf('.') + 1), metaDataSet);
            Map<Object, Object> responseBody = new HashMap();
            responseBody.put("success", 1);
            responseBody.put("url", "https://static.chulung.com/" + path.getFullPath());
            return success(responseBody);
        } catch (Exception e) {
            logger.error("文件上传失败", e);
            throw FileUploadExcetion.of(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
