package com.chulung.website.service.impl;

import java.time.LocalDateTime;

import com.chulung.website.mapper.ArticleMapper;
import com.chulung.website.mapper.UserTrackerMapper;
import com.chulung.website.mapper.VisitorInfoMapper;
import com.chulung.website.model.UserTracker;
import com.chulung.website.model.VisitorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.website.constant.Constants;
import com.chulung.website.service.VisitorInfoService;
import com.chulung.common.util.NetUtil;
import com.chulung.common.util.StringUtil;

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
		if (StringUtil.isBlank(href)) {
			return;
		}
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
