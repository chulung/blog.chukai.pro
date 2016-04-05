package com.wenchukai.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenchukai.blog.mapper.ArticleMapper;
import com.wenchukai.tracker.common.Constant;
import com.wenchukai.tracker.mapper.UserTrackerMapper;
import com.wenchukai.tracker.mapper.VisitorInfoMapper;
import com.wenchukai.tracker.model.UserTracker;
import com.wenchukai.tracker.model.VisitorInfo;
import com.wenchukai.tracker.service.VisitorInfoService;
import com.wenchukai.util.NetUtil;

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
		articleVisitor.setTuid(NetUtil.getCookieValue(Constant.TUID));
		String href = articleVisitor.getHref();
		int indexOf = href.indexOf('?');
		if (indexOf > 0) {
			articleVisitor.setParamsString(href.substring(indexOf));
			articleVisitor.setHref((href = href.substring(0, indexOf)));
		}
		userTrackerMapper.insertSelective(articleVisitor);
		if (href.matches("^/article/\\d+$")) {
			Integer articleId = Integer.valueOf(href.substring(href.lastIndexOf('/') + 1));
			UserTracker record = new UserTracker();
			record.setTuid(NetUtil.getCookieValue(Constant.TUID));
			record.setHref(href);
			if (userTrackerMapper.selectCount(record) <= 1) {

			}
		}
	}

}
