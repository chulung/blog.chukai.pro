package com.chulung.website.service.impl;

import com.chulung.common.util.DateUtils;
import com.chulung.common.util.JsonUtil;
import com.chulung.website.exception.FileUploadExcetion;
import com.chulung.website.exception.ServerRuntimeException;
import com.chulung.website.mapper.FileInfoMapper;
import com.chulung.website.model.FileInfo;
import com.chulung.website.service.FileUploadService;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.rmi.server.ExportException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chulung on 2017/5/9.
 */
@Service
public class FileUploadServiceImpl extends BaseService implements FileUploadService {
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public StorePath upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Set<MateData> metaDataSet = new HashSet();
        metaDataSet.add(new MateData("creator", "system"));
        metaDataSet.add(new MateData("createDate", DateUtils.format(new Date())));
        try {
            String fid = buildFid(file);
            FileInfo record = new FileInfo();
            record.setFid(fid);
            record = this.fileInfoMapper.selectOne(record);
            if (record != null) {
                return StorePath.praseFromUrl(record.getFullPath());
            }
            StorePath path = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
                    fileName.substring(fileName.lastIndexOf('.') + 1), metaDataSet);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setFid(fid);
            fileInfo.setFullPath(path.getFullPath());
            fileInfo.setMatedata(JsonUtil.toJsonString(metaDataSet));
            fileInfoMapper.insert(fileInfo);
            return path;
        } catch (Exception e) {
            throw new ServerRuntimeException("文件上传失败", e);
        }
    }

    /**
     * 构建文件唯一标识，md5+文件头尾各4个byte
     *
     * @param file
     * @return
     * @throws Exception
     */
    private String buildFid(MultipartFile file) throws Exception {
        byte[] bytes = file.getBytes();
        MessageDigest md5 = null;
        md5 = MessageDigest.getInstance("MD5");
        md5.update(bytes);
        BigInteger bi = new BigInteger(1, md5.digest());
        return bi.toString(16) + new String(bytes, 0, 4) + new String(bytes, bytes.length - 4, 4);
    }

}
