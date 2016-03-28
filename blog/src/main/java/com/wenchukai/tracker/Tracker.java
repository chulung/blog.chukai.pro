package com.wenchukai.tracker;

import java.util.Date;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.Globals;
import org.springframework.stereotype.Component;

import com.wenchukai.bean.BaseComponent;
import com.wenchukai.blog.service.VisitorInfoService;
import com.wenchukai.tracker.bean.VisitorInfo;
import com.wenchukai.util.NetUtil;
import com.wenchukai.util.StringUtil;

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
				Thread.sleep(1000L);
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
			String userAgent = request.getHeader("User-Agent");
			String ip = NetUtil.getIpAddr(request);
			Date accessTime = new Date();
			String accessUrl = request.getRequestURL().toString()
					+ (StringUtil.isEmpty(request.getQueryString()) ? "" : ("?" + request.getQueryString()));
			accessUrl = request.getAttribute(Globals.DISPATCHER_REQUEST_PATH_ATTR).toString();
			visitorInfos.put(new VisitorInfo(ip, userAgent, accessTime, accessUrl));
		} catch (InterruptedException e) {
			this.errorLog(e);
		}
	}
}
