package com.chulung.dfs.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.chulung.common.base.BaseController;
import com.chulung.common.util.DateUtils;

@RequestMapping("/")
@RestController
public class DfsController extends BaseController {
	@Autowired
	protected FastFileStorageClient storageClient;

	@RequestMapping(value = { "/", "" })
	public @ResponseBody String index() {
		return "<h1>Hi DFS!</h1>";
	}

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public @ResponseBody ModelMap postFile(@RequestParam(value = "file", required = false) MultipartFile file1,
			@RequestParam(value = "editormd-image-file", required = false) MultipartFile file2) {
		MultipartFile file = file1 != null ? file1 : file2;
		if (file == null) {
			return errorMap();
		}
		Set<MateData> metaDataSet = new HashSet<MateData>();
		metaDataSet.add(new MateData("creator", "system"));
		metaDataSet.add(new MateData("createDate", DateUtils.format(new Date())));
		String fileName = file.getOriginalFilename();
		try {
			StorePath path = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
					fileName.substring(fileName.lastIndexOf('.') + 1), metaDataSet);
			return successMap().addAttribute("message", "上传成功").addAttribute("url",
					"//static.chulung.com/" + path.getFullPath());
		} catch (Exception e) {
			logger.error("文件上传失败", e);
			return errorMap();
		}
	}
}
