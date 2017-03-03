package com.chulung.website.service;

import com.chulung.website.model.UserTracker;
import com.chulung.website.model.VisitorInfo;

public interface VisitorInfoService {

	void insertVisitorInfo(VisitorInfo visitorInfo);

	void insertUserTracker(UserTracker articleVisitor);
}
