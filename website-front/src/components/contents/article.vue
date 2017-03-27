<template>
  <div class="col-md-8 ">
    <transition :css="false"
                @before-enter="beforeEnter"
                @enter="enter"
                @leave="leave">
      <div>
        <article class="post format-standard hentry transition-item" key="article" v-if="article">
          <header class="entry-header">
            <div class="heading-title">
              <h1 class="entry-title">{{article.title}}</h1>
            </div>
            <div class="entry-meta">
              <span class="posted-on">发表于 {{article.createTime}}</span>
              <span class="byline"> by <span class="author vcard"><a class="url fn n"
                                                                     href="javascript:;">{{article.author}}</a></span></span>
              <span class="meta-viewer">{{article.visitCount}} 点击</span>
              <span class="reading-estimation">{{article.commentCount||0}} 评论</span>
              <span class="">分享至:<a
                :href="'http://service.weibo.com/share/share.php?url=https://chulung.com/article/'+article.id+'&appkey=2897075133&title=【'+article.title+'】'+article.summary+'&pic='+article.pic"
                target="_blank">
                <i class="fa fa-weibo"></i></a></span>
            </div><!-- .entry-meta -->
          </header><!-- .entry-header -->
          <div class="entry-content" v-html="article.content">
          </div><!-- .entry-content -->
          <p>原文链接:<a :href="'https://chulung.com/article/'+article.id">https://chulung.com/article/{{article.id}}</a>
          </p>
          <footer class="entry-footer">
            <span class="cat-links">发表在 <a href="https://chulung.com">chulung's craft</a></span>
            <template v-if="article.tags">
          <span class="tags-links">标签
          <template v-for="tag in article.tags.split(',')">
                <router-link :to="'/tag/'+tag">{{tag}}</router-link>,
          </template>
          </span>
            </template>
          </footer><!-- .entry-footer -->
        </article><!-- .post -->
        <div class="related-posts" :class="{none:loading}" key="related" v-if="relevancies">
          <h3>相似文章</h3>
          <div class="row">
            <div class="col-md-3 col-sm-6" v-for="item in relevancies">
              <div class="post-container">
                <div class="post-thumbnail">
                  <router-link :to="'/article/'+item.id"><img :src="defaultPic(item.pic)" :alt="item.title"/>
                  </router-link>
                </div><!-- .post-thumbnail -->
                <span class="post-meta">{{item.createTime}}</span>
                <h2 class="post-title">
                  <router-link :to="'/article/'+item.id">{{item.title}}</router-link>
                </h2>
              </div><!-- .post-container -->
            </div><!-- .col-md-3 -->
          </div><!-- .row -->
        </div>
        <div class="comments-area" :class="{none:loading}" key="comments">
          <div class="comment-respond">
            <h3 class="comment-reply-title">评论
              <small></small>
            </h3>
            <section class="widget widget_recent_comments">
              <ul v-if="comments">
                <li style="display: list-item;" v-for="item in comments"><span class="comment-author-link"><a
                  :href="item.website">{{item.userName}}:</a></span>
                  <p>{{item.comment}}</p></li>
              </ul>
            </section>
              <form class="form-horizontal comment-form">
                <div class="form-group">
                  <div class="col-sm-12">
                    <textarea id="comment" class="form-control" rows="6" v-model="comment.comment"
                              placeholder="*内容 :" maxlength="300" required="required"></textarea>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-4">
                    <input id="userName" v-model="comment.userName" value="" class="form-control" placeholder="*名字 :"
                           type="text" maxlength="20" required="required">
                  </div>
                  <div class="col-sm-4">
                    <input id="email" name="email" value="" class="form-control" placeholder="*邮箱 :"
                           v-model="comment.email" maxlength="50" required="required">
                  </div>
                  <div class="col-sm-4">
                    <input v-model="comment.website" type="url" value="" class="form-control" maxlength="100"
                           placeholder="你的网址 :"/>
                  </div>
                </div>
                <p class="form-submit">
                  <input name="submit" @click="submitComment" class="btn btn-danish btn-lg btn-block" value="提交"
                         type="button">
                </p>
              </form><!-- #commentform -->
          </div><!-- #respond -->
        </div><!-- #comments -->
      </div>
    </transition>
  </div>
</template>
<script>
  import axios from 'axios'
  const Velocity = require('Velocity')
  export default {
    data () {
      return {
        article: {},
        comments: {},
        relevancies: {},
        loading: true,
        comment: {comment: '', userName: '', email: '', website: '', articleId: ''}
      }
    },
    created () {
      this.fetchArticleData()
    },
    watch: {
      article (newValue, oldValue) {
        this.fetchComments()
        this.fetchRelevancy()
      },
      $route (to, from) {
        this.fetchArticleData()
      }
    },
    methods: {
      fetchArticleData () {
        axios.get('/article/' + (this.$route.params.id || this.$route.meta.id)).then(response => {
          this.article = response.data
          this.comment.articleId = this.article.id
          this.loading = false
        })
      },
      fetchComments () {
        axios.get('/comments/list/' + this.article.id, {
          params: {
            page: 1
          }
        }).then(response => {
          this.comments = response.data.list
        })
      },
      fetchRelevancy () {
        axios.get('/article/relevancy/' + this.article.id).then(response => {
          this.relevancies = response.data
        })
      },
      submitComment () {
        axios.put('/', JSON.parse(JSON.stringify(this.$data.comment))).then(response => {

        }).catch(e => {
          console.log(e)
        })
      },
      defaultPic: function (pic) {
        return pic || '/static/img/logo.jpg'
      },
      beforeEnter: function (el) {
        el.style.opacity = 0
        Velocity(el, {translateX: -250})
      },
      enter: function (el, done) {
        Velocity(
          el,
          {opacity: 1, height: '100%', duration: 1000, translateX: 0},
          {duration: 1000, complete: done}
        )
      },
      leave: function (el, done) {
        Velocity(
          el,
          {opacity: 0, translateX: 250},
          {duration: 1000, complete: done}
        )
      }
    }
  }
</script>

