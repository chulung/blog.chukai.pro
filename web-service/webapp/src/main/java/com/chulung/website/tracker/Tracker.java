package com.chulung.website.tracker;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.chulung.website.model.BaseComponent;
import com.chulung.website.model.VisitorInfo;
import com.chulung.website.service.VisitorInfoService;

@Component
public class Tracker extends BaseComponent implements InitializingBean{

	private TransferQueue<VisitorInfo> visitorInfos = new LinkedTransferQueue<>();
	@Resource
	private VisitorInfoService visitorInfoService;

	public void track(HttpServletRequest request) {
		try {
			visitorInfos.put(new VisitorInfo(request));
		} catch (InterruptedException e) {
			this.errorLog(e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.logger.info("traker  start");
		new Thread(() -> {
			try {
				do {
					this.visitorInfoService.insertVisitorInfo(this.visitorInfos.take());
				} while (true);
			} catch (Exception e) {
				this.errorLog(e);
			}
		}).start();
	}
}
