package pro.chukai.web.controller;

import pro.chukai.web.model.ArticleVisit;
import pro.chukai.web.service.VisitorInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pro.chukai.web.model.ArticleVisit;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/tracker")
public class TrackerController extends BaseController {
    @Resource
    private VisitorInfoService visitorInfoService;

    @RequestMapping(value = "/articleClick", method = RequestMethod.POST)
    public ResponseEntity postUserTracker(@RequestBody ArticleVisit articleVisit) {
        visitorInfoService.handArticleVisit(articleVisit);
        return success();
    }

}
