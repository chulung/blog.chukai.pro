<template>
  <div class="col-md-4" id="sidebar-div">
    <section class="widget widget_search">
      <form role="search" method="get" class="search-form" action="/search">
        <label>
          <span class="screen-reader-text">Search for:</span>
          <input class="search-field" placeholder="CSearch …" value="" name="word" title="Search for:"
                 type="search">
        </label>
        <input class="search-submit" value="Search" type="submit">
      </form><!-- search-form -->
    </section><!-- .widget_search -->
    <section class="widget danish_widget_about">
      <div class="about-author-container">
        <div class="about-author-info">
          <h2 class="widget-title">chulung</h2>
          <span class="author-subtitle">Developer</span>
          <div class="author-description">
            <p>目前从事后端开发，关注网站架构，平时也从前端折腾到数据库，感觉正在走向全栈，业余时会下厨，旅行，健身...</p>
            <a href="/about" class="more-link"><span class="moretext">More</span></a>
          </div><!-- .author-description -->
          <div class="author-footer">
            <div class="author-social">
              <a href="https://github.com/chulung" target="_blank" rel="external nofollow"><i
                class="fa fa-github"></i></a>
              <a href="mailto:chulung@chulung.com" rel="external nofollow" target="_blank"><i
                class="fa fa-envelope"></i></a>
              <a href="http://weibo.com/chulung" target="_blank" rel="external nofollow"><i
                class="fa fa-weibo"></i></a>
            </div><!-- .author-social -->
          </div><!-- .author-footer -->
        </div><!-- .about-author-info -->
      </div><!-- .about-author-container -->
    </section><!-- .danish_widget_about -->

      <section class="widget danish_widget_popular_entries ">
        <h2 class="widget-title">热门</h2>
        <ul>
          <li style="display: list-item;" v-for="item in popularArticles">
            <div class="popular-entry-container">
              <div class="entry-image"><img :src="defaultPic(item.pic)"></div>
              <div class="entry-content">
                <h4 class="entry-title"><a ref="bookmark" :href="'/article/'+item.id">{{item.title}}</a>
                </h4>
                <span class="entry-category"><a
                  :href="'/articles?page=1&typeId='+item.typeId+'#content'">{{item.typeName}}</a></span>
                <span class="entry-datetime">{{item.createTime}}</span>
              </div>
            </div>
          </li>
        </ul>
      </section><!-- .danish_widget_popular_entries -->
      <section class="widget widget_archive">
        <h2 class="widget-title">归档</h2>
        <ul id="articleFilings">
          <li v-for="item in articleFilings"><a
            :href="'/monthFilings/'+ yearMonth(item)">{{yearMonth(item)}}</a>({{item.count}})
          </li>
        </ul>
      </section><!-- .widget_archive -->
    <section class="widget widget_text">
      <h2 class="widget-title">常用网址</h2>
      <div class="textwidget">
        <ul>
          <li><a href="https://github.com/chulung" rel="external nofollow" target="_blank">GitHub</a></li>
          <li><a href="http://www.cnblogs.com/chulung/" rel="external nofollow" target="_blank">博客园</a></li>
        </ul>
      </div><!-- .textwidget -->
    </section>
      <section class="widget widget_recent_comments">
        <h2 class="widget-title">最新评论</h2>
        <ul id="recentcomments">
          <li style="display: list-item;" v-for="item in recentlyComments">
            <span class="comment-author-link">{{item.userName}}</span> :
            <a :href="'/article/'+item.articleId+'#comments'+item.id">{{item.comment}}
            </a>
          </li>
        </ul>
      </section><!-- .widget_recent_comments -->
  </div><!-- .col-md-4 -->
</template>
<script>
  export default{
    data: function () {
      return {
        'articleFilings': [{
          'yearMonth': {'year': 2017, 'month': 'FEBRUARY', 'leapYear': false, 'monthValue': 2},
          'count': 5
        }, {
          'yearMonth': {'year': 2016, 'month': 'DECEMBER', 'leapYear': true, 'monthValue': 12},
          'count': 1
        }],
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
    },
    methods: {
      defaultPic: function (pic) {
        return pic || 'static/logo.jpg'
      },
      yearMonth: function (item) {
        return item.yearMonth.year + '-' + item.yearMonth.monthValue
      }
    }
  }
</script>
