package com.chulung.website.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.chulung.website.constant.Constants;
import com.chulung.website.dto.out.CommentsOut;
import com.chulung.website.dto.out.PageOut;
import com.chulung.website.exception.MethodRuntimeExcetion;
import com.chulung.website.mapper.CommentsMapper;
import com.chulung.website.model.Comments;
import com.chulung.website.model.PaginationResult;
import com.chulung.website.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import com.chulung.website.dto.in.PageIn;
import com.chulung.common.util.NetUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;

@Service
public class CommentsServiceImpl extends BaseService implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Resource(name = "oneMin")
    private Cache cache;

    @Override
    public boolean postComments(Comments comments) {
        String curSessionId = NetUtil.getCurSessionId() + "_cmts";
        Integer count = cache.get(curSessionId, () -> 0);
        if (count > 1) {
            throw new MethodRuntimeExcetion("评论过于频繁，请稍候!");
        } else {
            cache.put(curSessionId, ++count);
        }
        checkExistBlank(comments.getArticleId(), comments.getComment(), comments.getUserName());
        comments.setCreateTime(LocalDateTime.now());
        commentsMapper.insertSelective(comments);
        commentsMapper.recalcCommentsCountForArticle(comments.getArticleId());
        return comments.getId() != null;// 插入成功后会自动注入id
    }

    @Override
    public PageOut<CommentsOut> listComments(int pageNum, int articleId) {
        PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE);
        Comments record = new Comments();
        record.setArticleId(articleId);
        Page<Comments> page = (Page<Comments>) commentsMapper.select(record);
        PageOut<CommentsOut> pageOut = new PageOut<>(page.getPageNum(), page.getPages());
        pageOut.setList(page.stream().map(a->new CommentsOut().buildFromModel(a)).collect(Collectors.toList()));
        return pageOut;
    }

    @Override
    public List<Comments> listRecentlyComments() {
        PageHelper.startPage(1, 3);
        Page<Comments> page = (Page<Comments>) commentsMapper.selectAll();
        // 防暴露email
        return page.stream().map(p -> {
            p.setEmail(null);
            return p;
        }).collect(Collectors.toList());
    }
}
