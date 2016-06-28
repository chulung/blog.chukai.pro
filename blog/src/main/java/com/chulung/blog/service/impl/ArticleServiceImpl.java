package com.chulung.blog.service.impl;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chulung.blog.dto.ArticleDto;
import com.chulung.blog.dto.ArticleFiling;
import com.chulung.blog.dto.CommonInfo;
import com.chulung.blog.dto.TreeNode;
import com.chulung.blog.enumerate.DictionaryTypeEnum;
import com.chulung.blog.enumerate.PublishStatusEnum;
import com.chulung.blog.enumerate.SiteEnum;
import com.chulung.blog.enumerate.TreeNodeTypeEnum;
import com.chulung.blog.exception.GlobalMethodRuntimeExcetion;
import com.chulung.blog.mapper.ArticleDraftHistoryMapper;
import com.chulung.blog.mapper.ArticleDraftMapper;
import com.chulung.blog.mapper.ArticleMapper;
import com.chulung.blog.mapper.CikiMapper;
import com.chulung.blog.mapper.DictionaryMapper;
import com.chulung.blog.mapper.MetaCLBlogLogMapper;
import com.chulung.blog.model.Article;
import com.chulung.blog.model.ArticleDraft;
import com.chulung.blog.model.Ciki;
import com.chulung.blog.model.Dictionary;
import com.chulung.blog.model.MetaCLBlogLog;
import com.chulung.blog.model.User;
import com.chulung.blog.service.ArticleService;
import com.chulung.blog.session.WebSessionSupport;
import com.chulung.common.util.DateUtils;
import com.chulung.common.util.NumberUtil;
import com.chulung.metaclblog.MetaWeblog;
import com.chulung.metaclblog.struct.Post;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {
	private static final int PAGE_SIZE = 10;
	public static String METACKBLOG_COMMENTS = "<p>本文于%s从<a href=\"//blog.chulung.com\">Chu lung's blog</a>自动同步同步,<a href=\"http://blog.chulung.com/article/%s\">访问原文</a><p>";

	@Resource
	private WebSessionSupport webSessionSupport;

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ArticleDraftHistoryMapper articleDraftHistoryMapper;
	@Autowired
	private ArticleDraftMapper articleDraftMapper;
	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Autowired
	private MetaCLBlogLogMapper metaWeBlogLogMapper;
	@Autowired
	private CikiMapper cikiMapper;
	/**
	 * 博客园的metaweblog接口
	 */
	@Resource(name = "cnblogMetaWeblog")
	private MetaWeblog cnblogMetaWeblog;

	public Article findArticleById(Integer id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public boolean update(ArticleDraft articleDraft) {
		User user = this.webSessionSupport.getCurUser().get();
		// 备份老版本
		articleDraftHistoryMapper.insertToArticleDraftHistory(articleDraft.getId());
		ArticleDraft oldDraft = this.articleDraftMapper.selectByPrimaryKey(articleDraft.getId());
		articleDraft.setArticleId(oldDraft.getArticleId());
		articleDraft.setUpdateTime(LocalDateTime.now());
		articleDraft.setMender(user.getNickName());
		articleDraft.setVersion(oldDraft.getVersion() + 1);
		// 判断是否发布文章
		if (PublishStatusEnum.PUBLISHED == articleDraft.getIsPublish()) {
			Article article = Article.of(articleDraft);
			if (article.getId() == null) {
				article.setAuthor(user.getNickName());
				article.setCreateTime(LocalDateTime.now());
				articleMapper.insertSelective(article);
				articleDraft.setArticleId(article.getId());
			} else {
				articleMapper.updateByPrimaryKeySelective(article);
			}
		}
		if (!(articleDraftMapper.updateByPrimaryKeySelective(articleDraft) == 1)) {
			throw new GlobalMethodRuntimeExcetion("修改草稿失败");
		}
		return true;
	}

	@Override
	public List<Dictionary> findAllArticleTypes() {
		Dictionary record = new Dictionary();
		record.setDictType(DictionaryTypeEnum.ARTICLE_TYPE);
		return dictionaryMapper.select(record);
	}

	@Override
	@Transactional
	public void insert(ArticleDraft articleDraft) {
		User user = this.webSessionSupport.getCurUser().get();
		articleDraft.setUpdateTime(LocalDateTime.now());
		articleDraft.setMender(user.getNickName());
		articleDraft.setVersion(1);
		articleDraft.setAuthor(user.getNickName());
		articleDraft.setIsDelete(0);
		articleDraft.setCreateTime(LocalDateTime.now());
		if (PublishStatusEnum.PUBLISHED == articleDraft.getIsPublish()) {
			Article article = Article.of(articleDraft);
			int key = 0;
			if ((key = articleMapper.insertSelective(article)) <= 0) {
				throw new RuntimeException("插入文章失败");
			}
			articleDraft.setArticleId(key);
		}
		if (this.articleDraftMapper.insertSelective(articleDraft) <= 0) {
			throw new RuntimeException("插入草稿失败");
		}
	}

	@Override
	public ArticleDraft findArticleDraft(Integer id) {
		return this.articleDraftMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void deleteArticleDraft(Integer id) {
		ArticleDraft articleDraft = this.articleDraftMapper.selectByPrimaryKey(id);
		if (articleDraft != null) {
			if (PublishStatusEnum.PUBLISHED == articleDraft.getIsPublish()) {
				Article record = new Article();
				record.setId(articleDraft.getArticleId());
				record.setIsDelete(1);
			}
			ArticleDraft record = new ArticleDraft();
			record.setId(id);
			record.setIsDelete(1);
		}
		throw new GlobalMethodRuntimeExcetion("草稿不存在,id=" + id);
	}

	/**
	 * 推送博客文章至其他网站
	 * 
	 * @param article
	 * @return
	 */
	public boolean pushArticle(Article article) {
		MetaCLBlogLog metaCLBlogLog = this.metaWeBlogLogMapper.selectOne(new MetaCLBlogLog(article.getId()));
		Post post = new Post();
		post.setTitle(article.getTitle());
		post.setDateCreated(DateUtils.toDate(article.getCreateTime()));
		post.setDescription(article.getContext()
				+ String.format(METACKBLOG_COMMENTS, DateUtils.format(LocalDateTime.now()), article.getId()));
		if (metaCLBlogLog != null) {
			post.setPostid(metaCLBlogLog.getPostId());
			try {
				// 发送编辑请求
				cnblogMetaWeblog.editPost(post, true);
				MetaCLBlogLog record = new MetaCLBlogLog();
				record.setId(metaCLBlogLog.getId());
				record.setLastestPostTime(LocalDateTime.now());
				metaWeBlogLogMapper.updateByPrimaryKeySelective(record);
			} catch (XmlRpcException e) {
				logger.error("", e);
			}

		} else {
			try {
				// 发送新建博客请求
				String postId = cnblogMetaWeblog.newPost(article.getId().toString(), post, true);
				MetaCLBlogLog record = new MetaCLBlogLog(postId, article.getId(), LocalDateTime.now(),
						SiteEnum.CNBLOGS);
				metaWeBlogLogMapper.insertSelective(record);
			} catch (XmlRpcException e) {
				logger.error("", e);
			}
		}
		return true;

	}

	@Override
	public List<TreeNode> getCategoryTreeNode() {
		return Arrays.asList(this.getArticleTreeNode(), getCikiTreeNodeByParentId(0).get(0));
	}

	private TreeNode getArticleTreeNode() {
		TreeNode treeNode = new TreeNode(null, "Blog", TreeNodeTypeEnum.BLOG);
		treeNode.setNodes(this.findAllArticleTypes().parallelStream().map(a -> {
			TreeNode t = new TreeNode(Integer.valueOf(a.getDictValue()), a.getDictDesc(), TreeNodeTypeEnum.BLOG_CATE);
			ArticleDraft record = new ArticleDraft();
			record.setTypeId(Integer.valueOf(a.getDictValue()));
			t.setNodes(this.articleDraftMapper.selectTileList(record).parallelStream().map(at -> {
				return new TreeNode(at.getId(), at.getTitle(), TreeNodeTypeEnum.ARTICLE);
			}).collect(Collectors.toList()));
			return t;
		}).collect(Collectors.toList()));
		return treeNode;
	}

	private List<TreeNode> getCikiTreeNodeByParentId(Integer parentId) {
		Ciki record = new Ciki();
		record.setParentId(parentId);
		return this.cikiMapper.getCikiTitelListByParentId(record).parallelStream().map(c -> {
			TreeNode node = new TreeNode(c.getId(), c.getTitle());
			if (c.getType() == 0) {
				node.setType(c.getId() == 0 ? TreeNodeTypeEnum.CIKI : TreeNodeTypeEnum.CIKI_CATE);
				node.setNodes(this.getCikiTreeNodeByParentId(c.getId()));
			} else {
				node.setType(TreeNodeTypeEnum.CIKI_TEXT);
			}
			return node;
		}).collect(Collectors.toList());
	}

	private static String generatingSummary(String context) {
		String replaceAll = context.replaceAll("</?.*?>", "");
		return replaceAll.length() > 120 ? replaceAll.substring(0, 120) + "..." : replaceAll;
	}

	public PageInfo<Article> selectBySelectiveForBlog(Optional<Integer> startPage, Integer typeId) {
		ArticleDto bean = new ArticleDto();
		bean.setTypeId(typeId);
		bean.setIsDelete(0);
		PageHelper.startPage(startPage.get(), PAGE_SIZE);
		Page<Article> page = (Page<Article>) articleMapper.selectBySelectiveForBlog(bean);
		PageInfo<Article> info = new PageInfo<Article>();
		info.setList(convertToSummary(page));
		info.setTotal(page.getTotal());
		return info;
	}

	@Override
	public List<Article> getBlogsByYearMonth(Integer year, Integer month) {
		if (NumberUtil.isRangeNotIn(year, 2014, 2993) && NumberUtil.isRangeNotIn(month, 1, 12)) {
			return Collections.emptyList();
		}
		ArticleDto bean = new ArticleDto();
		bean.setCreateTimeStart(LocalDateTime.of(year, month, 1, 0, 0));
		bean.setCreateTimeEnd(LocalDateTime.of(year, month, 1, 0, 0).plus(1, ChronoUnit.MONTHS));
		return convertToSummary(articleMapper.selectBySelectiveForBlog(bean));
	}

	@Override
	public CommonInfo getCommonInfo() {
		// 归档信息
		List<ArticleFiling> list = new ArrayList<ArticleFiling>();
		ArticleDto bean = new ArticleDto();
		bean.setIsDelete(0);
		articleMapper.selectBySelectiveForBlog(bean).stream().parallel().map(article -> article.getCreateTime())
				.map(localDate -> YearMonth.of(localDate.getYear(), localDate.getMonthValue()))
				.collect(Collectors.groupingByConcurrent(yearMonth -> yearMonth, Collectors.counting()))
				.forEach((k, v) -> {
					list.add(new ArticleFiling(k, v.intValue()));
				});
		list.sort((o1, o2) -> {
			return o2.compareTo(o1);
		});
		return new CommonInfo(list);
	}

	public static List<Article> convertToSummary(List<Article> articles) {
		return articles.stream().map(a -> {
			a.setContext(generatingSummary(a.getContext()));
			return a;
		}).collect(Collectors.toList());
	}
}
