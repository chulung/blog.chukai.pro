<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div class="col-md-8 page-default">
    <transition-group
      :css="false"
      @before-enter="beforeEnter"
      @enter="enter"
      @leave="leave">
      <article class="post format-standard hentry" v-for="(article,index) in articles" :key="article"
               :data-index="index">
        <div class="post-container">
          <div class="post-content">
            <div class="heading-title heading-small">
              <span class="post-meta-cat"><router-link
                :to="'/articles?columnId='+article.columnId+'#content'">{{article.columnName}}</router-link></span>
              <h2>
                <router-link :to="'/article/'+article.id" rel="bookmark">{{article.title}}</router-link>
              </h2>
            </div><!-- .heading-small -->

            <div class="post-meta">
            <span class="posted-on">
              发表于 <a href="javascript:;" class=" entry-date"
                     :datetime="article.createTime">{{article.createTime}}</time> </a>
            </span><!-- .posted-on -->
              <span class="byline">
                by <span class="author vcard"><a class="url fn n" href="javascript:;">{{article.author}}</a></span>
              </span><!-- .byline -->
              <span class="reading-estimation">{{article.visitCount}} 点击</span>
            </div><!-- .post-meta -->
            <p>
              {{article.summary}}.
              <router-link :to="'/article/'+article.id" class="more-link">
                <span class="moretext">阅读全文</span>
              </router-link><!-- .more-link -->
            </p>
          </div><!-- .post-content -->
        </div><!-- .post-container -->
      </article><!-- .post -->
    </transition-group>
    <nav class="navigation posts-navigation" role="navigation" :class="{none:hideNav}">
      <div class="nav-links" style="z-index: -1">
        <div class="nav-next" v-if="nextPage">
          <a @click="loadMore">加载更多<i class="fa fa-spinner" :class="{'fa-pulse':loading}"></i></a>
        </div>
      </div>
    </nav><!-- .navigation -->
  </div><!-- .col-md-8 -->
</template>
<script>
  import axios from 'axios'
  const Velocity = require('Velocity')
  const delayCoefficient = 750 // 延迟系数
  const paths = {
    'index': '/articles',
    'articles': '/articles',
    'tag': '/tag/:tag',
    'column': '/articles',
    'search': '/search'
  }
  export default{
    data () {
      return {articles: {}, nextPage: null, columnId: null, loading: false, hideNav: true}
    },
    created () {
      this.fetchArticleData()
    },
    watch: {
      $route (to, from) {
        this.fetchArticleData()
      }
    },
    methods: {
      loadMore () {
        this.loading = true
        this.fetchArticleData(true)
      },
      fetchArticleData (loadMore) {
        let path = paths[this.$route.name].replace(':tag', this.$route.params.tag)
        axios.get(path, {
          params: {
            page: loadMore ? this.nextPage : this.$route.query.page,
            column: this.$route.params.column,
            year: this.$route.query.year,
            month: this.$route.query.month
          }
        }).then(response => {
          if (loadMore) {
            this.articles.push.apply(this.articles, response.data.list)
          } else {
            this.articles = response.data.list
          }
          this.nextPage = response.data.nextPage
          setTimeout(() => {
            this.hideNav = false
            this.loading = false
          }, delayCoefficient * this.articles.length)
        }).catch(e => {
          console.log(e)
        })
      },
      beforeEnter: function (el) {
        el.style.opacity = 0
        el.style.height = 0
        const options = this.loading ? {translateY: -250} : {translateX: -250}
        Velocity(el, options)
      },
      enter: function (el, done) {
        var delay = el.dataset.index * delayCoefficient
        setTimeout(function () {
          Velocity(
            el,
            {opacity: 1, height: '100%', duration: 1000, translateY: 0, translateX: 0},
            {duration: 1000, complete: done}
          )
        }, delay)
      },
      leave: function (el, done) {
        Velocity(
          el,
          {opacity: 0, translateY: 125},
          {duration: 500, complete: done}
        )
      }
    }
  }
</script>
