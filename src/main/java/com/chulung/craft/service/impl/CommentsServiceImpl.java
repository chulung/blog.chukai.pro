package com.chulung.craft.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.chulung.craft.exception.MethodRuntimeExcetion;
import com.chulung.craft.mapper.CommentsMapper;
import com.chulung.craft.model.Comments;
import com.chulung.craft.model.PaginationResult;
import com.chulung.craft.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chulung.craft.dto.PageIn;
import com.chulung.ccache.Cache;
import com.chulung.ccache.builder.CacheBuilder;
import com.chulung.common.util.NetUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class CommentsServiceImpl extends BaseService implements CommentsService {

	@Autowired
	private CommentsMapper commentsMapper;

	private Cache cache = CacheBuilder.config(100).addLiveMillesCacheStrategy(60, false).generateCache();

	@Override
	public boolean postComments(Comments comments) {
		String curSessionId = NetUtil.getCurSessionId() + "_cmts";
		Integer count = cache.get(curSessionId);
		if (count == null) {
			cache.put(curSessionId, 1);
		} else if (count > 1) {
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
		PageHelper.orderBy("id desc");
		Page<Comments> page = (Page<Comments>) commentsMapper.selectAll();
		// 防暴露email
        return page.stream().map(p->{p.setEmail(null); return p;}).collect(Collectors.toList());
    }
}
