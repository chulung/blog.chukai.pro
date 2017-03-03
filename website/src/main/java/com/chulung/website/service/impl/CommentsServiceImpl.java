package com.chulung.website.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.chulung.website.exception.MethodRuntimeExcetion;
import com.chulung.website.mapper.CommentsMapper;
import com.chulung.website.model.Comments;
import com.chulung.website.model.PaginationResult;
import com.chulung.website.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.chulung.website.dto.PageIn;
import com.chulung.common.util.NetUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;

@Service
public class CommentsServiceImpl extends BaseService implements CommentsService {

	@Autowired
	private CommentsMapper commentsMapper;

	@Resource(name = "oneMin")
	private Cache cache;

	@Override
	public boolean postComments(Comments comments) {
		String curSessionId = NetUtil.getCurSessionId() + "_cmts";
		Integer count = cache.get(curSessionId,()->0);
		if (count > 1) {
			throw new MethodRuntimeExcetion("评论过于频繁，请稍候!");
		} else {
			cache.put(curSessionId, ++count);
		}
		checkExistBlank(comments.getArticleId(),comments.getComment(), comments.getUserName());
		comments.setCreateTime(LocalDateTime.now());
		commentsMapper.insertSelective(comments);
		commentsMapper.recalcCommentsCountForArticle(comments.getArticleId());
		return comments.getId() != null;// 插入成功后会自动注入id
	}

	@Override
	public PaginationResult<Comments> listComments(PageIn<Comments> pageIn) {
		checkExistBlank(pageIn.getRecord().getArticleId());
		PageHelper.startPage(pageIn.getPage(), pageIn.getPageSize());
		Page<Comments> page = (Page<Comments>) commentsMapper.select(pageIn.getRecord());
		// 防暴露email
		page.forEach(p -> p.setEmail(null));
		return PaginationResult.of(page);
	}

	@Override
	public List<Comments> listRecentlyComments() {
		PageHelper.startPage(1, 3);
		Page<Comments> page = (Page<Comments>) commentsMapper.selectAll();
		// 防暴露email
        return page.stream().map(p->{p.setEmail(null); return p;}).collect(Collectors.toList());
    }
}
