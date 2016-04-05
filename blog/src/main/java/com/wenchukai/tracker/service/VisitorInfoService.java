package com.wenchukai.tracker.service;

import com.wenchukai.tracker.model.UserTracker;
import com.wenchukai.tracker.model.VisitorInfo;

public interface VisitorInfoService {

	void insertVisitorInfo(VisitorInfo visitorInfo);

	void insertUserTracker(UserTracker articleVisitor);
}
