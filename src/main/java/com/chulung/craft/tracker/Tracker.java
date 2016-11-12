package com.chulung.craft.tracker;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.chulung.craft.model.BaseComponent;
import com.chulung.craft.model.VisitorInfo;
import com.chulung.craft.service.VisitorInfoService;

@Component
public class Tracker extends BaseComponent {

	private TransferQueue<VisitorInfo> visitorInfos = new LinkedTransferQueue<>();
	@Resource
	private VisitorInfoService visitorInfoService;

	public Tracker() {
		this.logger.info("traker  start");
		new Thread(() -> {
			try {
				Thread.sleep(10000);
				do {
					this.visitorInfoService.insertVisitorInfo(this.visitorInfos.take());
				} while (true);
			} catch (Exception e) {
				this.errorLog(e);
			}
		}).start();
	}

	public void track(HttpServletRequest request) {
		try {
			visitorInfos.put(new VisitorInfo(request));
		} catch (InterruptedException e) {
			this.errorLog(e);
		}
	}
}
