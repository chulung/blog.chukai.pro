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
  import axios from 'axios'
  export default{
    data () {
      return {
        'articleFilings': [],
        'recentlyComments': [],
        'recommendedArticles': []
      }
    },
    created () {
      axios.get('sidebarInfo').then((response) => {
        this.articleFilings = response.data.articleFilings
        this.recentlyComments = response.data.recentlyComments
        this.recommendedArticles = response.data.recommendedArticles
      })
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
