package pro.chukai;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by chukai on 2017/2/28.
 */
@EnableWebMvc
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "pro.chukai.web.mapper")
@Import(FdfsClientConfig.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
