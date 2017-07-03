package com.chulung.website.service;

import com.github.tobato.fastdfs.domain.StorePath;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chulung on 2017/5/9.
 */
public interface FileUploadService {
    StorePath upload(MultipartFile file);
}
