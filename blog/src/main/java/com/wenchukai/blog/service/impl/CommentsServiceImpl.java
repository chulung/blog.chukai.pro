package com.wenchukai.blog.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wenchukai.blog.dto.PageIn;
import com.wenchukai.blog.mapper.CommentsMapper;
import com.wenchukai.blog.model.Comments;
import com.wenchukai.blog.model.PaginationResult;
import com.wenchukai.blog.service.CommentsService;

@Service
public class CommentsServiceImpl extends BaseService implements CommentsService {

	@Autowired
	private CommentsMapper commentsMapper;

	@Override
	public boolean postComments(Comments comments) {
		checkExistBlank(comments.getArticleId(), comments.getEmail(), comments.getUserName());
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
		//防暴露email
		page.stream().forEach(p -> p.setEmail(null));
		return PaginationResult.of(page);
	}

}
