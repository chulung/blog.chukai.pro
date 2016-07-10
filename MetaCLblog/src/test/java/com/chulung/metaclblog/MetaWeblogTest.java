package com.chulung.metaclblog;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chulung.metaclblog.MetaWeblog;
import com.chulung.metaclblog.struct.Post;


@Ignore//忽略防止构建失败 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:metackblog.xml")
//测试顺序
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class MetaWeblogTest {
	@Resource
	private MetaWeblog cnblogMetaWeblog;
	private static Post post= new Post();
	@Before 
	public void setUp(){
		post.setDateCreated(new Date());
		post.setDescription("<h1>hello world!</h1>");
		post.setTitle("metaWeblog test");
	}
	
	@Test
	public void test1NewPost() throws XmlRpcException {
		String postId = cnblogMetaWeblog.newPost("1", post, true);
		assertNotNull(postId);
		post.setPostid(postId);
	}

	@Test
	public void test2EditPost() throws XmlRpcException {
		post.setDescription("<h3>hello world edit</h3>");
		assertThat(this.cnblogMetaWeblog.editPost(post, true), is(true));
	}

	@Test
	public void test3GetPost() throws XmlRpcException {
		post.setDescription("<h3>hello world edit</h3>");
		assertNull(this.cnblogMetaWeblog.getPost("1"));
		assertThat(this.cnblogMetaWeblog.getPost(post.getPostid()).getDescription(), equalTo(post.getDescription()));
	}

	@Test
	public void test4DeletePost() throws XmlRpcException {
		assertThat(this.cnblogMetaWeblog.deletePost(post.getPostid()), is(true));
	}

	@Test
	public void testGetConfigInfo() {
	}

}
