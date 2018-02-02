package pro.chukai.web.model;

import pro.chukai.common.util.NetUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class VisitorInfo extends BaseModel {


    private static final long serialVersionUID = 3076762908203898932L;

    private String ip;

    private String userAgent;

    private LocalDateTime accessTime;

    private String accessUrl;

    private String serverName;

    private String sessionId;

    private String referer;

    public VisitorInfo(String ip, String userAgent, LocalDateTime accessTime, String accessUrl, String serverName,
                       String sessionId, String referer) {
        super();
        this.ip = ip;
        this.userAgent = userAgent;
        this.accessTime = accessTime;
        this.accessUrl = accessUrl;
        this.setServerName(serverName);
        this.sessionId = sessionId;
        this.referer = referer;
    }

    public VisitorInfo(HttpServletRequest request) {
        this(NetUtil.getIpAddr(), NetUtil.getUserAgent(),
                LocalDateTime.now(), NetUtil.getAccessUrl(), request.getServerName(),
                NetUtil.getCurSessionId(), request.getHeader("Referer"));
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl == null ? null : accessUrl.trim();
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }
}