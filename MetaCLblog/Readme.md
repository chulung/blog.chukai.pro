#MetaCLblog


##关于
这是一个利用MetaWeblog接口的博客推送工具

基于xmlrpc，用于从[Chu Lung's Blog](http://blog.chulung.com)向cnblog等博客网站自动同步文章。

目前只支持cnblog 见 [http://www.cnblogs.com/chulung/](http://www.cnblogs.com/chulung/)，csdn的接口不兼容,有时间再重构。

##使用
新建metackblog.properties文件 内容如下

	cnblog.username=登录名
	cnblog.password=密码
	cnblog.serverurl=http://rpc.cnblogs.com/metaweblog/chulung
	
serverurl格式为`http://rpc.cnblogs.com/metaweblog/博客名`,如上是我的

spring bean 配置

	<bean id="propertyConfigurer"
		class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="locations">
			<list>
				<value>classpath:metackblog.properties</value>
			</list>
		</property>
		<property name="fileEncoding" value="UTF-8"></property>
	</bean>
	<context:component-scan base-package="com.chulung.metaclblog" />
	<bean id="cnblogConfig" class="com.chulung.metackblog.config.ConfigInfo">
		<property name="userName" value="${cnblog.username}"></property>
		<property name="password" value="${cnblog.password}"></property>
		<property name="serverUrl" value="${cnblog.serverurl}"></property>
	</bean>
	<bean id="cnblogMetaWeblog" class="com.chulung.metaclblog.MetaWeblog">
		<property name="configInfo" ref="cnblogConfig"></property>
	</bean>

注入
	
	@Resource
	private MetaWeblog cnblogMetaWeblog;	
	
具体使用可参考单元测试com.chulung.metaclblog.MetaWeblogTest

