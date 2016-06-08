package com.chulung.blog.service;

import com.chulung.blog.model.UserTracker;
import com.chulung.blog.model.VisitorInfo;

public interface VisitorInfoService {

	void insertVisitorInfo(VisitorInfo visitorInfo);

	void insertUserTracker(UserTracker articleVisitor);
}
