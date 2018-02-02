package pro.chukai.web.service;

import org.apache.xmlrpc.XmlRpcException;

/**
 * Created by chukai on 2016/11/8.
 */
public interface MetaClBlogLogService {
    void pushBlog() throws XmlRpcException;

    void pushBlog(Integer id) throws XmlRpcException;
}
