<template>
  <div class="col-md-3">
    <!--search-->
    <div class="margin-bottom-50">
      <section class="widget widget_search">
        <form role="search" method="get" action="/search" class="search-form">
          <label>
            <input placeholder="CSearch …" value="" name="word" title="Search for:" class="search-field" type="search">
          </label>
          <input value="Search" class="search-submit" type="submit">
        </form>
      </section>
    </div>
    <!--end search-->

    <!-- Social Shares -->
    <div class="margin-bottom-50">
      <h2 class="title-v4">一起玩耍</h2>
      <ul class="blog-social-shares">
        <li>
          <i class="rounded-x git fa fa-github"></i>
          <a class="rounded-3x" href="https://github.com/chulung" target="_blank" rel="external nofollow">Github</a>
          <!--<span class="counter">31,702</span>-->
        </li>
        <li>
          <i class="rounded-x wb fa  fa-weibo"></i>
          <a class="rounded-3x" href="http://weibo.com/chulung" target="_blank" rel="external nofollow">微博</a>
        </li>
        <li>
          <i class="rounded-x mail fa fa-envelope"></i>
          <a class="rounded-3x" href="mailto:chulung@chulung.com" target="_blank" rel="external nofollow">Email</a>
        </li>
      </ul>
    </div>
    <!-- End Social Shares -->
    <!-- Blog Thumb v2 -->
    <div class="margin-bottom-50">
      <h2 class="title-v4">热门</h2>
      <template v-for="item in popularArticles">
        <div class="blog-thumb blog-thumb-circle" :class="{'margin-bottom-15':index<popularArticles.length-1}">
          <div class="blog-thumb-hover">
            <img class="rounded-x" :src="item.pic || '/static/logo.jpg'" alt="">
            <a class="hover-grad" :href="'/article/'+item.id"><i class="fa fa-link"></i></a>
          </div>
          <div class="blog-thumb-desc">
            <h3><a :href="'/article/'+item.id">{{item.title}}</a></h3>
            <ul class="blog-thumb-info">
              <li>Mar 6, 2015</li>
              <li><a href="#"><i class="fa fa-comments"></i> 0</a></li>
            </ul>
          </div>
        </div>
      </template>
    </div>
    <!-- End Blog Thumb v2 -->
    <template v-if="recentlyComments">
      <div class="margin-bottom-50">
        <h2 class="title-v4">最新评论</h2>
        <template v-for="item in recentlyComments">
          <div class="blog-thumb-v3">
            <small><a :href="item.website || 'javascript:;'" rel="external nofollow">{{item.userName}}</a></small>
            <h3><a :href="'/article/'+item.articleId+'#comments'+item.id">{{item.comment}}</a></h3>
          </div>
          <hr class="hr-xs">
        </template>
      </div>
    </template>
    <!-- End Blog Thumb v3 -->
  </div>
</template>
<script>
  export default{
    data: function () {
      let data = {
        'articleFilings': [{
          'yearMonth': {'year': 2017, 'month': 'FEBRUARY', 'leapYear': false, 'monthValue': 2},
          'count': 5
        }, {
          'yearMonth': {'year': 2016, 'month': 'DECEMBER', 'leapYear': true, 'monthValue': 12},
          'count': 1
        }, {
          'yearMonth': {'year': 2016, 'month': 'NOVEMBER', 'leapYear': true, 'monthValue': 11},
          'count': 3
        }, {
          'yearMonth': {'year': 2016, 'month': 'OCTOBER', 'leapYear': true, 'monthValue': 10},
          'count': 1
        }, {
          'yearMonth': {'year': 2016, 'month': 'JULY', 'leapYear': true, 'monthValue': 7},
          'count': 1
        }, {
          'yearMonth': {'year': 2016, 'month': 'APRIL', 'leapYear': true, 'monthValue': 4},
          'count': 2
        }, {
          'yearMonth': {'year': 2016, 'month': 'MARCH', 'leapYear': true, 'monthValue': 3},
          'count': 2
        }, {
          'yearMonth': {'year': 2016, 'month': 'FEBRUARY', 'leapYear': true, 'monthValue': 2},
          'count': 1
        }, {
          'yearMonth': {'year': 2016, 'month': 'JANUARY', 'leapYear': true, 'monthValue': 1},
          'count': 2
        }, {
          'yearMonth': {'year': 2015, 'month': 'DECEMBER', 'leapYear': false, 'monthValue': 12},
          'count': 2
        }, {
          'yearMonth': {'year': 2015, 'month': 'SEPTEMBER', 'leapYear': false, 'monthValue': 9},
          'count': 1
        }, {'yearMonth': {'year': 2014, 'month': 'JULY', 'leapYear': false, 'monthValue': 7}, 'count': 1}],
        'tags': [{'tagName': '规划', 'count': 4}, {'tagName': '原理', 'count': 3}, {
          'tagName': '性能优化',
          'count': 3
        }, {'tagName': 'nginx', 'count': 2}, {'tagName': '架构', 'count': 2}, {
          'tagName': '分布式',
          'count': 2
        }, {'tagName': '年度总结', 'count': 2}, {'tagName': '数学', 'count': 1}, {
          'tagName': 'FastDFS',
          'count': 1
        }, {'tagName': '前端', 'count': 1}, {'tagName': '序列化', 'count': 1}, {
          'tagName': '文件系统',
          'count': 1
        }, {'tagName': '设计模式', 'count': 1}, {'tagName': 'Freemarker', 'count': 1}, {
          'tagName': 'Tomcat',
          'count': 1
        }, {'tagName': '单例模式', 'count': 1}, {'tagName': '影响力', 'count': 1}, {
          'tagName': '项目总结',
          'count': 1
        }, {'tagName': 'HTTP2', 'count': 1}, {'tagName': '全栈', 'count': 1}, {
          'tagName': '单元测试',
          'count': 1
        }, {'tagName': '心理学', 'count': 1}, {'tagName': '模块化编程', 'count': 1}, {
          'tagName': '高并发',
          'count': 1
        }, {'tagName': 'java', 'count': 1}, {'tagName': '内网穿透', 'count': 1}, {
          'tagName': '热部署',
          'count': 1
        }, {'tagName': 'lucene', 'count': 1}, {'tagName': '函数式编程', 'count': 1}, {
          'tagName': '安全',
          'count': 1
        }, {'tagName': '搜索', 'count': 1}, {'tagName': '算法', 'count': 1}, {'tagName': 'memcached', 'count': 1}],
        'recentlyComments': [{
          'id': 30,
          'articleId': 36,
          'comment': '写的非常棒！ 感谢！',
          'createTime': '2017-02-21 11:37:38',
          'userName': 'HANS'
        }, {
          'id': 29,
          'articleId': 64,
          'comment': '我竟然看完了，沙发。',
          'createTime': '2017-02-01 13:41:39',
          'userName': '一葫芦酱油'
        }, {
          'id': 28,
          'articleId': 20,
          'comment': '请问你的fastdfs服务端也是搭在低配的阿里云服务器上么？网站的所有组件都是在一台阿里云的机器上？',
          'createTime': '2016-12-08 14:02:42',
          'userName': 'zjg23'
        }],
        'popularArticles': [{
          'id': 36,
          'title': '内网穿透原理及实现一：C/S，P2P模式原理',
          'createTime': '2016-07-10 00:16:45',
          'typeId': 1,
          'pic': '//static.chulung.com/group1/M00/00/00/cHx_F1eBBruAR78pAAAmTFcjUIw845.png',
          'typeName': '技能'
        }, {
          'id': 64,
          'title': '为什么改变很难发生',
          'createTime': '2017-02-01 12:36:48',
          'typeId': 2,
          'typeName': '心悟'
        }, {
          'id': 20,
          'title': '关于我',
          'createTime': '2016-03-27 08:33:53',
          'typeId': 4,
          'pic': '//static.chulung.com/group1/M00/00/00/cHx_F1b31x6ASf2iAAAfnIyLLQI109_150x150.jpg',
          'typeName': '未归类'
        }, {
          'id': 19,
          'title': '分布式系统的架构思路',
          'createTime': '2016-03-27 17:49:46',
          'typeId': 1,
          'pic': '//static.chulung.com/group1/M00/00/00/cHx_F1b3oW6AeD3zAAFPwp7x3vU490.jpg',
          'typeName': '技能'
        }],
        'recommendedArticles': [{
          'id': 67,
          'title': '种一棵树最好的时间是十年前,其次是现在',
          'createTime': '2017-02-12 22:58:08',
          'updateTime': '2017-02-18 17:34:09',
          'author': 'chulung',
          'typeId': 2,
          'isDelete': 'N',
          'commentCount': 0,
          'visitCount': 38,
          'typeName': '心悟',
          'summary': '这不是一篇鸡汤，只是一个回头的娃的故事罢了。\n2016年堪称一个人生转折点，经历了太多的事情，这篇文章就当作一年的总结吧，虽然又拖延了两月才写完，不过有了更多的反思，反而更能看清自己，这个借口还...'
        }, {
          'id': 63,
          'title': '从限流削峰到性能优化,谈1号店抽奖系统架构实践',
          'createTime': '2016-12-06 20:33:10',
          'updateTime': '2017-02-25 15:45:20',
          'author': 'chulung',
          'typeId': 1,
          'isDelete': 'N',
          'commentCount': 0,
          'visitCount': 82,
          'pic': 'https://static.chulung.com/group1/M00/00/01/cHx_F1hECJKADY91AAC3U7eZJws076.png',
          'typeName': '技能',
          'summary': '这篇文章算是我在工作的第一个年头里关于架构方面的收获与思考的一篇总结性的文章吧，感觉还是有些深度的，所以尝试投稿到InfoQ上，果真被收录了，很开心。从7月底开始动笔，中间因为各种偷懒和一些难以...'
        }]
      }
      return data
    }
  }
</script>
<style>
  .search-submit {
    background-color: #5bc0de;
    color: rgb(255, 255, 255);
    border: 1px solid rgb(49, 176, 213);
  }

  .search-field {
    color: #5bc0de;
  }

  /*--------------------------------------------------
  [Blog Social Shares]
  ----------------------------------------------------*/

  .blog-social-shares {
    padding-left: 0;
    list-style: none;
  }

  .blog-social-shares li {
    width: 100%;
    margin-bottom: 10px;
    display: inline-block;
  }

  .blog-social-shares li i {
    color: #fff;
    width: 30px;
    height: 30px;
    font-size: 18px;
    line-height: 30px;
    margin-right: 10px;
    text-align: center;
    display: inline-block;
  }

  .blog-social-shares li i.git {
    background: #000000;
  }

  .blog-social-shares li i.mail {
    background: #159ceb;
  }

  .blog-social-shares li i.wb {
    background: #dc4a38;
  }

  .blog-social-shares li a {
    top: -1px;
    color: #777;
    position: relative;
  }

  .blog-social-shares li a:hover {
    text-decoration: none;
  }

  .blog-social-shares li span {
    float: right;
    display: block;
    margin-top: 6px;
  }

</style>
