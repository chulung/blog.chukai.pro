package com.chulung.tracker.service;

import com.chulung.tracker.model.UserTracker;
import com.chulung.tracker.model.VisitorInfo;

public interface VisitorInfoService {

	void insertVisitorInfo(VisitorInfo visitorInfo);

	void insertUserTracker(UserTracker articleVisitor);
}
