package pro.chukai.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.chukai.web.enumerate.LogLevel;
import pro.chukai.web.enumerate.LogType;
import pro.chukai.web.mapper.AppLogMapper;
import pro.chukai.web.model.AppLog;
import pro.chukai.web.processor.GithookProcessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

/**
 * @author chukai
 */
@RestController
@RequestMapping("/openapi")
public class OpenApiController extends BaseController {
    @Autowired
    private GithookProcessor githookProcessor;
    @RequestMapping("/githooks/portfolio")
    public void portfolioHook() {
       githookProcessor.portfolioHook();
    }
}
