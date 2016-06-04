package com.chulung.blog.service.impl;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.chulung.blog.dto.ArticleFiling;
import com.chulung.blog.dto.Blog;
import com.chulung.blog.dto.CommonInfo;
import com.chulung.blog.mapper.ArticleMapper;
import com.chulung.blog.model.Article;
import com.chulung.blog.service.BlogService;
import com.chulung.common.util.NumberUtil;

@Service
@CacheConfig
public class BlogServiceImpl extends BaseService implements BlogService {
	private static final int PAGE_SIZE = 10;
	@Autowired
	private ArticleMapper articleMapper;

	public PageInfo<Blog> selectBySelectiveForBlog(Optional<Integer> startPage, Integer typeId) {
		Article bean = new Article();
		bean.setTypeId(typeId);
		bean.setIsDelete(0);
		PageHelper.startPage(startPage.get(), PAGE_SIZE);
		Page<Article> page = (Page<Article>) articleMapper.selectBySelectiveForBlog(bean);
		PageInfo<Blog> info = new PageInfo<Blog>();
		info.setList(convertToBlog(page));
		info.setTotal(page.getTotal());
		return info;
	}

	@Override
	public List<Blog> getBlogsByYearMonth(Integer year, Integer month) {
		if (NumberUtil.isRangeNotIn(year, 2014, 2993) && NumberUtil.isRangeNotIn(month, 1, 12)) {
			return Collections.emptyList();
		}
		Article bean = new Article();
		bean.setCreateTimeStart(LocalDateTime.of(year, month, 1, 0, 0));
		bean.setCreateTimeEnd(LocalDateTime.of(year, month, 1, 0, 0).plus(1, ChronoUnit.MONTHS));
		return convertToBlog(articleMapper.selectBySelectiveForBlog(bean));
	}

	@Override
	public CommonInfo getCommonInfo() {
		// 归档信息
		List<ArticleFiling> list = new ArrayList<ArticleFiling>();
		Article bean = new Article();
		bean.setIsDelete(0);
		articleMapper.selectBySelectiveForBlog(bean).stream().parallel().map(article -> article.getCreateTime())
				.map(localDate -> YearMonth.of(localDate.getYear(), localDate.getMonthValue()))
				.collect(Collectors.groupingByConcurrent(yearMonth -> yearMonth, Collectors.counting()))
				.forEach((k, v) -> {
					list.add(new ArticleFiling(k, v.intValue()));
				});
		list.sort((o1, o2) -> {
			return o2.compareTo(o1);
		});
		return new CommonInfo(list);
	}

	public static List<Blog> convertToBlog(List<Article> articles) {
		return articles.stream().map(a -> {
			return Blog.valueOf(a);
		}).collect(Collectors.toList());
	}
}
