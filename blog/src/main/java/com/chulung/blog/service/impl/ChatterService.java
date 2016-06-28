package com.chulung.blog.service.impl;

import java.util.List;

import com.chulung.blog.dto.PageIn;
import com.chulung.blog.model.Chatter;

public interface ChatterService {
	List<Chatter> getchatterList(PageIn<Chatter> pageIn);
}
