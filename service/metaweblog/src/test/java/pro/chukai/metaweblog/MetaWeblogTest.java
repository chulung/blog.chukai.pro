package pro.chukai.metaweblog;

import org.assertj.core.api.Assertions;
import pro.chukai.common.util.JsonUtil;
import pro.chukai.metaweblog.config.ConfigInfo;
import pro.chukai.metaweblog.struct.Post;
import pro.chukai.metaweblog.util.ReflectUtil;
import pro.chukai.metaweblog.xmlrpc.XmlRpcExecute;
import pro.chukai.test.BaseTest;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by chukai on 2017/5/22.
 */
public class MetaWeblogTest extends BaseTest {
    private MetaWeblog metaWeblog;

    private XmlRpcClient xmlRpcClient;
    private ConfigInfo configInfo;
    private Post post;

    @Before
    public void setUp() {
        metaWeblog = new MetaWeblog();
        configInfo = new ConfigInfo();
        configInfo.setServerUrl("https://chukai.pro");
        configInfo.setUserName("chukai");
        configInfo.setPassword("123456");
        configInfo.setSiteName("chukai's craft");
        metaWeblog.setConfigInfo(configInfo);

        post = new Post();
        post.setDateCreated(new Date());
        post.setTitle("the title");
        post.setDescription("this is a blog");

        XmlRpcExecute xmlRpcExecute = (XmlRpcExecute) ReflectionTestUtils.getField(metaWeblog, "xmlRpcExecute");
        xmlRpcClient = mock(XmlRpcClient.class);
        ReflectionTestUtils.setField(xmlRpcExecute, "client", xmlRpcClient);


    }

    @Test
    public void newPost() throws Exception {

        Object[] pramas = {"1", configInfo.getUserName(), configInfo.getPassword(), ReflectUtil.toXMLRPCParams(post), true};
        String postId = "2";
        when(xmlRpcClient.execute(anyString(), any(Object[].class))).thenReturn(postId);
        assertThat(metaWeblog.newPost("1", post, true)).isEqualTo(postId);
    }

    @Test
    public void editPost() throws Exception {
        post.setPostid("1");
        when(xmlRpcClient.execute(anyString(), any(Object[].class))).thenReturn(true);
        assertThat(metaWeblog.editPost(post, true)).isTrue();
    }

    @Test
    public void getPost() throws Exception {
        when(xmlRpcClient.execute(anyString(), any(Objects[].class))).thenReturn(JsonUtil.readValue(JsonUtil.writeBytes(post), HashMap.class));
        Assertions.assertThat(metaWeblog.getPost("1")).isEqualToIgnoringGivenFields(post, "dateCreated");
    }

    @Test
    public void deletePost() throws Exception {
        when(xmlRpcClient.execute(anyString(), any(Object[].class))).thenReturn(true);
        assertThat(metaWeblog.deletePost("1")).isTrue();
    }
}