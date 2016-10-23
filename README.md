
整站源码:[chulung's craft](https://chulung.com) 

##chulung's craft

算是随便搞的一个博客吧，折腾下各种新玩意，写写文章，从服务器到前端纯手工打造；

部署在阿里云的一台低配linux上；

数据库用的mysql；

因为有图片等文件上传需求，所以顺便玩了下FastDFS；

环境jdk1.8，等9出来就换9；tomcat8作为web服务器，前面架了个nginx做代理转发，https等操作；

语言自然是java，通用框架无非是spring4，mybatis3之类的；

前端用的是Bootstrap+Jquery+RequireJS，考虑过AngularJS，目前没时间学，模板引擎Freemarker；

也有一些自制的轮子，如缓存(CCache)[https://github.com/chulung/CCache]，自动同步到其他博客的黑科技[MetaCLblog](https://github.com/chulung/MetaCLblog)之类的；

构建发布什么的自然要自动化，所以装了个Jenkins，配下Github的Webhook，再加上一个发布脚本，一提交代码就自动构建部署了；

大概就是这些东西了，其他的慢慢加。

##表结构 

[database.sql](/database.sql)

##致谢以下开源项目
markdown编辑器:[editor.md](https://github.com/pandao/editor.md)
基于Mybatis的通用mapper及分页插件:[Mapper & PageHelper](https://github.com/abel533/Mybatis-Spring)
思维导图编辑器:[kityminder-editor](https://github.com/fex-team/kityminder-editor)
FastDFS客户端:(java)[FastDFS_Client](https://github.com/chulung/FastDFS_Client)

##License

The MIT License.

Copyright (c) 2015-2016 chulung
