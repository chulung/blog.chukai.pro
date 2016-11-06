package com.chulung.craft.service;

import com.chulung.craft.model.UserTracker;
import com.chulung.craft.model.VisitorInfo;

public interface VisitorInfoService {

	void insertVisitorInfo(VisitorInfo visitorInfo);

	void insertUserTracker(UserTracker articleVisitor);
}
