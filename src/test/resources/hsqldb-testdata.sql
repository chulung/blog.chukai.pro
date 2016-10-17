
CREATE TABLE category (
  id integer NOT NULL PRIMARY KEY,
  parent_id integer  NOT NULL,
  category_name varchar(50) NOT NULL,
  category_type varchar(10) NOT NULL,
  article_id integer  ,
);

CREATE TABLE article (
  id integer NOT NULL ,
  title varchar(200) NOT NULL,
  context  varchar(2000),
  create_time datetime NOT NULL,
  update_time datetime NOT NULL,
  author varchar(20) NOT NULL ,
  mender varchar(20) ,
  type_id integer NOT NULL ,
  derivation_url varchar(200),
  version integer NOT NULL ,
  is_delete integer NOT NULL,
  chang_log varchar(1000),
  comment_count integer NOT NULL ,
  visit_count integer NOT NULL ,
  is_publish varchar(10) NOT NULL,
);
INSERT INTO category(id,parent_id,category_name,category_type,article_id) VALUES (1,0, 'Ciki', 'CIKI', null);
INSERT INTO category(id,parent_id,category_name,category_type,article_id) VALUES (2,1, 'db', 'CIKI', null);
INSERT INTO category(id,parent_id,category_name,category_type,article_id) VALUES (3,1, 'java', 'CIKI', null);
INSERT INTO category(id,parent_id,category_name,category_type,article_id) VALUES (4,3, 'java的热部署和热加载', 'CIKI', 1);
INSERT INTO category(id,parent_id,category_name,category_type,article_id) VALUES (5,3, 'java的热部署和热加载', 'CIKI', 3);

INSERT INTO article VALUES ('1', 'java的热部署和热加载', '<p>ps:热部署和热加载其实是两个类似但不同的概念，之前理解不深，so，这篇文章重构了下。</p>\n<h3 id=\"h3--\"><a name=\"一、热部署与热加载\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>一、热部署与热加载</h3><p>在应用运行的时升级软件，无需重新启动的方式有两种，热部署和热加载。</p>\n<p>对于Java应用程序来说，热部署就是在服务器运行时重新部署项目，热加载即在在运行时重新加载class，从而升级应用。</p>\n<h3 id=\"h3--\"><a name=\"二、实现原理\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>二、实现原理</h3><p>热加载的实现原理主要依赖java的类加载机制，在实现方式可以概括为在容器启动的时候起一条后台线程，定时的检测类文件的时间戳变化，如果类的时间戳变掉了，则将类重新载入。</p>\n<p>对比反射机制，反射是在运行时获取类信息，通过动态的调用来改变程序行为；<br>热加载则是在运行时通过重新加载改变类信息，直接改变程序行为。\n<p>热部署原理类似，但它是直接重新加载整个应用，这种方式会释放内存，比热加载更加干净彻底，但同时也更费时间。</p>', '2015-09-17 13:05:56', '2016-04-07 00:00:00', '初', '初', '1', null, '4', '0', null, '0', '207', 'PUBLISHED');