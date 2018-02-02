package pro.chukai.web.controller;

import pro.chukai.common.util.NetUtil;
import pro.chukai.web.dto.in.UserIn;
import pro.chukai.web.dto.out.ArticleDraftOut;
import pro.chukai.web.dto.out.PageOut;
import pro.chukai.web.model.ArticleDraft;
import pro.chukai.web.model.Column;
import pro.chukai.web.model.User;
import pro.chukai.web.service.ArticleService;
import pro.chukai.web.service.ColumnSevice;
import pro.chukai.web.service.UserService;
import pro.chukai.web.session.WebSessionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.chukai.web.dto.in.UserIn;
import pro.chukai.web.dto.out.ArticleDraftOut;
import pro.chukai.web.dto.out.PageOut;
import pro.chukai.web.model.Column;
import pro.chukai.web.model.User;
import pro.chukai.web.service.ArticleService;
import pro.chukai.web.service.ColumnSevice;
import pro.chukai.web.service.UserService;
import pro.chukai.web.session.WebSessionSupport;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * backend后台
 *
 * @author chukai
 */
@RestController
@RequestMapping("/api/cms")
public class CMSController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private WebSessionSupport webSessionSupport;

    @Autowired
    private ColumnSevice columnSevice;

    @RequestMapping("/login")
    public ResponseEntity getLogin() {
        return success("logined", this.webSessionSupport.islogIn());
    }

    /**
     * 登录操作
     *
     * @param user
     * @param response
     * @return
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ResponseEntity logIn(@RequestBody UserIn user, @RequestParam(required = false) String reUrl,
                                HttpServletResponse response) {
        User backend = userService.logInbackend(user);
        return success();
    }

    /**
     * 草稿列表
     *
     * @return
     */
    @RequestMapping("/articleDrafts")
    public
    @ResponseBody
    PageOut<ArticleDraftOut> articlesDrafts(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(required = false) Integer columnId) {
        return this.articleService.findArticleDraftsList(pageNum, columnId);
    }


    /**
     * 根据id查询草稿
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/articleDraft/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ArticleDraftOut getArticleDraft(@PathVariable Integer id) {
        return this.articleService.findArticleDraft(id);
    }

    /**
     * 保存修改草稿
     *
     * @param articleDraft
     * @return
     */
    @RequestMapping(value = "/articleDraft", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity putArticleDraft(@RequestBody ArticleDraft articleDraft) {
        this.articleService.update(articleDraft);
        return success();
    }

    /**
     * 保存新建草稿
     *
     * @param articleDraft
     * @return
     */
    @RequestMapping(value = "/articleDraft", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity postArticle(@RequestBody ArticleDraft articleDraft) {
        return success("id", this.articleService.insert(articleDraft));
    }

    /**
     * 删除草稿
     *
     * @return
     */
    @RequestMapping(value = "/articleDraft/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity deleteArticleDraft(@PathVariable Integer id) {
        this.articleService.deleteArticleDraft(id);
        return success();
    }

    /**
     * 注销
     *
     * @return
     */
    @RequestMapping("/logOut")
    public ResponseEntity logout(HttpServletResponse response) {
        webSessionSupport.logOut();
        Cookie cookie = new Cookie(NetUtil.SESSION_ID, "");
        cookie.setPath("/");// cookie 必须设置为根路径,否则会导致其他子路径无法拿到cookie
        response.addCookie(cookie);
        return success();
    }

    @RequestMapping("/columns")
    public
    @ResponseBody
    List<Column> getColumns() {
        return this.columnSevice.getAllColumns();
    }
}
