package com.chulung.website.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chulung.website.session.WebSessionSupport;
import com.chulung.common.util.DateUtils;
import com.chulung.common.util.ImageUtil;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

@RequestMapping("/fileUpload")
@RestController
public class FileUploadController extends BaseController {
	@Autowired
	private WebSessionSupport webSessionSupport;
	@Autowired
	protected FastFileStorageClient storageClient;

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public @ResponseBody ModelMap postFile(MultipartHttpServletRequest mRequest) {
		if (!webSessionSupport.islogIn()) {
			return errorMap("抱歉，本站不对外提供文件上传功能!");
		}
		Map<String, MultipartFile> map = mRequest.getFileMap();
		if (map.size() != 1) {
			return errorMap("文件个数必须为1个"); 
		}
		MultipartFile file = map.values().iterator().next();
		String fileName = file.getOriginalFilename();
		Set<MateData> metaDataSet = new HashSet<MateData>();
		metaDataSet.add(new MateData("creator", "system"));
		metaDataSet.add(new MateData("createDate", DateUtils.format(new Date())));
		try {
			StorePath path = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
					fileName.substring(fileName.lastIndexOf('.') + 1), metaDataSet);
			return successMap().addAttribute("message", "上传成功").addAttribute("url",
					"https://static.chulung.com/" + path.getFullPath());
		} catch (Exception e) {
			logger.error("文件上传失败", e);
			return errorMap();
		}

	}
}
