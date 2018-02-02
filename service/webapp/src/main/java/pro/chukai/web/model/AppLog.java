package pro.chukai.web.model;

import pro.chukai.web.enumerate.LogLevel;
import pro.chukai.web.enumerate.LogType;

import java.time.LocalDateTime;

public class AppLog extends BaseModel {

    private static final long serialVersionUID = 1775282535859780116L;
    private LogType type;
    private LogLevel level;
    private String log;
    private LocalDateTime createTime;

    public AppLog() {
    }

    public AppLog(LogType type, LogLevel level, String log) {
        this(type, level, log, LocalDateTime.now());
    }

    public AppLog(LogType type, LogLevel level, String log, LocalDateTime createTime) {
        super();
        this.type = type;
        this.level = level;
        this.log = log;
        this.setCreateTime(createTime);
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}
