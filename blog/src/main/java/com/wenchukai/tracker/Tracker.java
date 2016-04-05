package com.wenchukai.tracker;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.wenchukai.base.BaseComponent;
import com.wenchukai.tracker.common.Constant;
import com.wenchukai.tracker.model.VisitorInfo;
import com.wenchukai.tracker.service.VisitorInfoService;
import com.wenchukai.util.NetUtil;

@Component
public class Tracker extends BaseComponent {

	private TransferQueue<VisitorInfo> visitorInfos = new LinkedTransferQueue<VisitorInfo>();
	public static Tracker TRACKER;
	@Resource
	private VisitorInfoService visitorInfoService;

	public Tracker() {
		TRACKER = this;
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

	public static void track(HttpServletRequest request) {
		if (TRACKER != null) {
			TRACKER.putClientInfo(request);
		}
	}

	public void putClientInfo(HttpServletRequest request) {
		try {
			visitorInfos.put(new VisitorInfo(NetUtil.getIpAddr(request), request.getHeader("User-Agent"),
					LocalDateTime.now(), NetUtil.getAccessUrl(request), request.getServerName(),
					NetUtil.getCookieValue(Constant.TUID)));
		} catch (InterruptedException e) {
			this.errorLog(e);
		}
	}
}
