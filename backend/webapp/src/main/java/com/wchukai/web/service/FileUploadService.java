package com.wchukai.web.service;

import com.github.tobato.fastdfs.domain.StorePath;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wchukai on 2017/5/9.
 */
public interface FileUploadService {
    StorePath upload(MultipartFile file);
}
