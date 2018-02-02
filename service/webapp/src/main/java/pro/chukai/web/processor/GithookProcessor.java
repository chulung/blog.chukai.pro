package pro.chukai.web.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pro.chukai.web.enumerate.LogLevel;
import pro.chukai.web.enumerate.LogType;
import pro.chukai.web.mapper.AppLogMapper;
import pro.chukai.web.model.AppLog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chukai
 */
@Component
public class GithookProcessor {

    @Value("${githooks.portfolio.shell}")
    private String portfolioShell;
    @Autowired
    private AppLogMapper appLogMapper;
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(1), new ThreadPoolExecutor.DiscardPolicy());

    public void portfolioHook() {
        this.threadPoolExecutor.submit(new HookTask());
    }

    private class HookTask implements Runnable {

        @Override
        public void run() {

            AppLog appLog = new AppLog();
            try {
                Process ps = Runtime.getRuntime().exec(portfolioShell);
                ps.waitFor();
                BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                appLog.setLog(sb.toString());
            } catch (Exception e) {
                appLog.setLog(e.toString());
            }
            appLog.setCreateTime(LocalDateTime.now());
            appLog.setLevel(LogLevel.ERROR);
            appLog.setType(LogType.EXCEPTION);
            appLogMapper.insert(appLog);
        }
    }
}
