package com.chulung.blog.mapper;

import java.util.List;

import com.chulung.blog.model.Article;
import com.chulung.blog.model.Chatter;

public interface ChatterMapper extends BaseMapper<Article>{
	List<Chatter> selectList();
}