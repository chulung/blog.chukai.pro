package com.chulung.tracker;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.chulung.common.base.BaseComponent;
import com.chulung.common.util.NetUtil;
import com.chulung.tracker.common.Constant;
import com.chulung.tracker.model.VisitorInfo;
import com.chulung.tracker.service.VisitorInfoService;

@Component
public class Tracker extends BaseComponent {

	private TransferQueue<VisitorInfo> visitorInfos = new LinkedTransferQueue<VisitorInfo>();
	@Resource
	private VisitorInfoService visitorInfoService;

	public Tracker() {
		this.logger.info("traker  start");
		new Thread(() -> {
			try {
				Thread.sleep(10000);
				while (true) {
					this.visitorInfoService.insertVisitorInfo(this.visitorInfos.take());
				}
			} catch (Exception e) {
				this.errorLog(e);
			}
		}).start();
	}

	public void track(HttpServletRequest request) {
		try {
			visitorInfos.put(new VisitorInfo(NetUtil.getIpAddr(request), request.getHeader("User-Agent"),
					LocalDateTime.now(), NetUtil.getAccessUrl(request), request.getServerName(),
					NetUtil.getCookieValue(Constant.TUID)));
		} catch (InterruptedException e) {
			this.errorLog(e);
		}
	}
}
