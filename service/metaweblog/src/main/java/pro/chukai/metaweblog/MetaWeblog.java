package pro.chukai.metaweblog;

import pro.chukai.metaweblog.config.ConfigInfo;
import pro.chukai.metaweblog.struct.Post;
import pro.chukai.metaweblog.util.ReflectUtil;
import pro.chukai.metaweblog.xmlrpc.XmlRpcExecute;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.Map;

public class MetaWeblog {
    private Logger logger = LoggerFactory.getLogger(MetaWeblog.class);

    private XmlRpcExecute xmlRpcExecute;

    private ConfigInfo configInfo;

    public MetaWeblog() {
        try {
            xmlRpcExecute = new XmlRpcExecute();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 推送
     *
     * @param blogid  标识位，意义不明，随意填
     * @param post    post.Title,post.DateCreated,post.Description 必填
     * @param publish 是否发布
     * @return postid 目标站点返回的postid 编辑删除等请求必填参数
     * @throws XmlRpcException
     */
    public String newPost(String blogid, Post post, boolean publish) throws XmlRpcException {
        if (blogid == null || post == null || post.getDateCreated() == null || post.getDescription() == null
                || post.getTitle() == null) {
            throw new IllegalArgumentException(
                    "blogid,post,post.Title,post.DateCreated,post.Description cannot be null!");
        }
        Object[] pParams = {blogid, configInfo.getUserName(), configInfo.getPassword(), ReflectUtil.toXMLRPCParams(post), publish};
        return (String) this.xmlRpcExecute.execute(configInfo.getServerUrl(), "metaWeblog.newPost", pParams);
    }

    /**
     * @param post    post.postid 必填 指目标网站的返回postid
     * @param publish 是否发布
     * @return
     * @throws XmlRpcException
     */
    public boolean editPost(Post post, boolean publish) throws XmlRpcException {
        if (post == null || post.getPostid() == null) {
            throw new IllegalArgumentException("post or Postid cannot be null!");
        }
        Object[] pParams = {post.getPostid(), configInfo.getUserName(), configInfo.getPassword(), ReflectUtil.toXMLRPCParams(post),
                publish};
        return (boolean) this.xmlRpcExecute.execute(configInfo.getServerUrl(), "metaWeblog.editPost", pParams);
    }

    @SuppressWarnings("unchecked")
    public Post getPost(String postid) throws XmlRpcException {
        Object[] pParams = {postid, configInfo.getUserName(), configInfo.getPassword()};
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
        return result == null ? null : ReflectUtil.reflectFields(new Post(), (Map<String, Object>) result);
    }

    public boolean deletePost(String postid) throws XmlRpcException {
        Object[] pParams = {configInfo.getAppKey(), postid, configInfo.getUserName(), configInfo.getPassword(), true};
        return (boolean) this.xmlRpcExecute.execute(configInfo.getServerUrl(), "blogger.deletePost", pParams);
    }

    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

}
