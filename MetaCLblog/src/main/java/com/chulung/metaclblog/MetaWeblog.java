package com.chulung.metaclblog;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chulung.metackblog.config.ConfigInfo;
import com.chulung.metaclblog.struct.Post;
import com.chulung.metaclblog.xmlrpc.XmlRpcExecute;

public class MetaWeblog {
	private Logger logger=LoggerFactory.getLogger(MetaWeblog.class);
	@Resource
	private XmlRpcExecute xmlRpcExecute;

	private ConfigInfo configInfo;

	/**
	 * 推送
	 * 
	 * @param blogid
	 *            标识位，好像没具体含义
	 * @param post
	 *            post.Title,post.DateCreated,post.Description 必填
	 * @param publish
	 *            是否发布
	 * @return postid 目标站点返回的postid 编辑删除等请求必填参数
	 * @throws XmlRpcException 
	 */
	public String newPost(String blogid, Post post, boolean publish) throws XmlRpcException {
		if (blogid == null || post == null || post.getDateCreated() == null || post.getDescription() == null
				|| post.getTitle() == null) {
			throw new IllegalArgumentException(
					"blogid,post,post.Title,post.DateCreated,post.Description cannot be null!");
		}
		Object[] pParams = { blogid, configInfo.getUserName(), configInfo.getPassword(), post.toParams(), publish };
		return (String) this.xmlRpcExecute.execute(configInfo.getServerUrl(), "metaWeblog.newPost", pParams);
	}

	/**
	 * 
	 * @param post
	 *            post.postid 必填 指目标网站的返回postid
	 * @param publish
	 *            是否发布
	 * @return
	 * @throws XmlRpcException 
	 */
	public boolean editPost(Post post, boolean publish) throws XmlRpcException {
		if (post == null || post.getPostid() == null) {
			throw new IllegalArgumentException("post or Postid cannot be null!");
		}
		Object[] pParams = { post.getPostid(), configInfo.getUserName(), configInfo.getPassword(), post.toParams(),
				true };
		return (boolean) this.xmlRpcExecute.execute(configInfo.getServerUrl(), "metaWeblog.editPost", pParams);
	}

	@SuppressWarnings("unchecked")
	public Post getPost(String postid) throws XmlRpcException {
		Object[] pParams = { postid, configInfo.getUserName(), configInfo.getPassword() };
		Object result = null;
		try {
			result = this.xmlRpcExecute.execute(configInfo.getServerUrl(), "metaWeblog.getPost", pParams);
		} catch (XmlRpcException e) {
			if (e.code == 404) {
				logger.debug("post is not found", e);
				return null;
			}
			throw e;
		}
		return result == null ? null : new Post((Map<String, Object>) result);
	}

	public boolean deletePost(String postid) throws XmlRpcException {
		Object[] pParams = { configInfo.getAppKey(), postid, configInfo.getUserName(), configInfo.getPassword(), true };
			return (boolean) this.xmlRpcExecute.execute(configInfo.getServerUrl(), "blogger.deletePost", pParams);
	}

	public void setConfigInfo(ConfigInfo configInfo) {
		this.configInfo = configInfo;
	}

	public ConfigInfo getConfigInfo() {
		return configInfo;
	}

}
