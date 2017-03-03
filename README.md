
整站源码:[chulung's craft](https://chulung.com) 

##chulung's craft v2.x

我的博客第二版，比起上一版预计有这么些改动：

1. 框架全部转为springboot，配置注解化，不再使用xml配置。web服务器改为springboot的内嵌Tomcat。

2. 搜索和博客同步模块改为内嵌，不再手动依赖其他模块。

3. 缓存从自制轮子改用guava cache。

3. 前端从jquery+RequireJS+bootstrap+freemark逐渐转为vue.js+bootstrap+webpack,并为搜索引擎的SEO引入服务端渲染。

4. 持续集成准备从自建jenkins改为Travis,自己的服务器性能不够，实在有点撑不住一个jenkins平台，并准备引入静态代码分析。

5. 补充基于Junit+assertj的单元测试。

6. 考虑引入RPC，将java后端完全service化，前端改为Node环境。

7. 重构现有的栏目，分离出一个知识wiki。

8. 重构后台管理，现在是在太难看了。

9. JDK跟进升级1.9。

目前完成了1，2，3，正在做4，5，6



##表结构 

[chulung_craft.sql](/chulung_craft.sql)

##致谢以下开源项目

markdown编辑器:[editor.md](https://github.com/pandao/editor.md)

基于Mybatis的通用mapper及分页插件:[Mapper & PageHelper](https://github.com/abel533/Mybatis-Spring)

FastDFS客户端:(java)[FastDFS_Client](https://github.com/tobato/FastDFS_Client)

##License

The MIT License.

Copyright (c) 2015-2017 chulung
