<template>
  <div>
    <header class="page-header" v-show="errorMsg">
      <h1 class="page-title">{{errorMsg}}</h1>
    </header>
    <article class="post format-standard hentry " v-if="article.id">
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
            :href="'http://service.weibo.com/share/share.php?url=https://chulung.com/article/'+article.uri+'&appkey=2897075133&title=【'+article.title+'】'+article.summary+'&pic='+article.pic"
            target="_blank">
                <i class="fa fa-weibo"></i></a></span>
        </div><!-- .entry-meta -->
      </header><!-- .entry-header -->
      <div class="entry-content" v-html="article.content">
      </div><!-- .entry-content -->
      <p>原文链接:<a :href="'https://chulung.com/article/'+article.uri">https://chulung.com/article/{{article.uri}}</a>
      </p>
      <footer class="entry-footer">
        <span class="cat-links">发表在 <a href="https://chulung.com">初龙的博客</a></span>
        <template v-if="article.tags">
          <span class="tags-links">标签
          <template v-for="tag in article.tags.split(',')">
                <router-link :to="'/tag/'+tag">{{tag}}</router-link>,
          </template>
          </span>
        </template>
      </footer><!-- .entry-footer -->
    </article><!-- .post -->
    <div class="related-posts" key="related" v-if="relevancies.length">
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
    <div class="comments-area" key="comments" v-show="!errorMsg">
      <div class="comment-respond">
        <h3 class="comment-reply-title">评论
          <small></small>
        </h3>
        <section class="widget widget_recent_comments" v-if="comments.length">
          <ul>
            <li style="display: list-item;" v-for="item in comments"><span class="comment-author-link"><a
              :href="item.website || '#'">{{item.userName}}:</a></span>
              <p>{{item.comment}}</p></li>
          </ul>
        </section>
        <form class="form-horizontal comment-form">
          <div class="form-group">
            <div class="col-sm-12">
                    <textarea class="form-control" rows="6" v-model="comment.comment"
                              placeholder="*内容 :" v-validate="'required'" maxlength="300" name="comment"
                              title=""></textarea>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-4">
              <input v-model="comment.userName" name="userName" value="" class="form-control" placeholder="*名字 :"
                     type="text" maxlength="20" v-validate="'required'" title="">
            </div>
            <div class="col-sm-4">
              <input name="email" value="" v-validate="'required|email'" class="form-control" placeholder="*邮箱 :"
                     v-model="comment.email" maxlength="50" title="">
            </div>
            <div class="col-sm-4">
              <input v-model="comment.website" name="website" type="url" value="" class="form-control" maxlength="100"
                     placeholder="你的网址 :" title=""/>
            </div>
          </div>
          <p class="form-submit">
            <input name="submit" @click="validateBeforeSubmit" class="btn btn-danish btn-lg btn-block" value="提交"
                   type="button">
          </p>
        </form><!-- #commentform -->
      </div><!-- #respond -->
    </div><!-- #comments -->
  </div>
</template>
<script>
  import axios from '~plugins/axios'
  import common from '~plugins/common'
  import lscache from 'lscache'
  export default {
    head () {
      return {
        title: this.article.title
      }
    },
    data () {
      return {
        article: {title: ' '},
        comments: [],
        relevancies: [],
        comment: {},
        errorMsg: ''
      }
    },
    watch: {
      article (newValue, oldValue) {
        this.fetchComments()
        this.fetchRelevancy()
      }
    },
    asyncData ({params, error}) {
      return axios.get(`/article/${params.uri}`)
        .then((res) => {
          return {
            article: res.data,
            comment: {
              comment: '',
              userName: '',
              email: '',
              articleId: res.data.id,
              uri: res.data.uri
            }
          }
        })
        .catch((e) => {
          return {errorMsg: (e.response.status === 404 ? '哎呀！没有找到这篇文章。' : '查询文章出错了QAQ')}
        })
    },
    mounted () {
      this.postUT()
    },
    methods: {
      fetchComments () {
        axios.get('/comments', {
          params: {
            page: 1,
            articleId: this.article.id
          }
        }).then(response => {
          this.comments = response.data.list
        }).catch(e => {
          console.log(e)
        })
      },
      fetchRelevancy () {
        axios.get('/article/relevancy/' + this.article.id).then(response => {
          this.relevancies = response.data
        }).catch(e => {
          console.log(e)
        })
      },
      validateBeforeSubmit () {
        this.$validator.validateAll().then(result => {
          if (!result) {
            for (const k of Object.keys(this.comment)) {
              let msg = this.errors.first(k)
              if (msg) {
                window.$(document.getElementsByName(k)[0]).attr('title', msg).tooltip().focus()
                break
              }
            }
            return
          }

          axios.post('/comments', common.toJson(this.$data.comment)).then(response => {
            this.fetchComments()
          }).catch(e => {
            if (e.response.status === 409) {
              alert('操作太频繁！')
            }
            console.log(e)
          })
        })
      },
      defaultPic: function (pic) {
        return pic || '/img/logo.jpg'
      },
      postUT: function () {
        // 排除非浏览器环境
        if (!process.BROWSER_BUILD || !this.article.id) {
          return
        }
        let articleKey = `art_${this.article.id}`
        if (lscache.get(articleKey)) {
          return
        }
        axios.post('/tracker/articleClick', {
          'articleId': this.article.id,
          'sessionId': common.getSessionId()
        }).then(response => {
          lscache.set(articleKey, '1')
        }).catch(e => {
          console.log(e)
        })
      }
    }
  }
</script>

