package com.chulung.blog.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.blog.common.util.NetUtil;
import com.chulung.blog.constant.Constants;
import com.chulung.blog.mapper.ArticleMapper;
import com.chulung.blog.mapper.UserTrackerMapper;
import com.chulung.blog.mapper.VisitorInfoMapper;
import com.chulung.blog.model.UserTracker;
import com.chulung.blog.model.VisitorInfo;
import com.chulung.blog.service.VisitorInfoService;

@Service
public class VisitorInfoServiceImpl implements VisitorInfoService {
	@Autowired
	private VisitorInfoMapper visitorMapper;
	@Autowired
	private UserTrackerMapper userTrackerMapper;
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public void insertVisitorInfo(VisitorInfo visitorInfo) {
		visitorMapper.insertSelective(visitorInfo);
	}

	@Override
	public void insertUserTracker(UserTracker articleVisitor) {
		articleVisitor.setTuid(NetUtil.getCookieValue(Constants.TUID));
		String href = articleVisitor.getHref();
		int indexOf = href.indexOf('?');
		if (indexOf > 0) {
			articleVisitor.setParamsString(href.substring(indexOf));
			articleVisitor.setHref((href = href.substring(0, indexOf)));
		}
		articleVisitor.setCreateTime(LocalDateTime.now());
		userTrackerMapper.insertSelective(articleVisitor);
		if (href.matches(".*/article/\\d+$")) {
			Integer articleId = Integer.valueOf(href.substring(href.lastIndexOf('/') + 1));
			UserTracker record = new UserTracker();
			record.setTuid(NetUtil.getCookieValue(Constants.TUID));
			record.setHref(href);
			if (userTrackerMapper.selectCount(record) <= 1) {
				//自增阅读次数
				articleMapper.incrementVisitCount(articleId);
			}
		}
	}

}
