<template>
  <div class="col-md-8">
    <article class="post format-standard hentry">
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
      <p>原文链接:<a :href="'https://chulung.com/article/'+article.id">https://chulung.com/article/{{article.id}}</a></p>
      <footer class="entry-footer">
        <span class="cat-links">发表在 <a href="https://chulung.com">chulung's craft</a></span>
        <template v-if="article.tags">
          <span class="tags-links">标签
          <template v-for="tag in article.tags.split(',')">
                    <a href="/'tag/'+tag">{{tag}}</a>,
          </template>
          </span>
        </template>
      </footer><!-- .entry-footer -->
    </article><!-- .post -->
    <div id="related-top"></div>
    <template v-if="result && result.length">
      <div class="related-posts none" id="related-posts">
        <h3>猜你喜欢</h3>
        <div class="row">
          <div class="col-md-3 col-sm-6" v-for="item in result">
            <div class="post-container">
              <div class="post-thumbnail">
                <a :href="'/article/'+item.id"><img :src="defaultPic(item.pic)" :alt="item.title"/></a>
              </div><!-- .post-thumbnail -->
              <span class="post-meta">{{item.createTime}}</span>
              <h2 class="post-title"><a :href="'/article/'+item.id">{{item.title}}</a></h2>
            </div><!-- .post-container -->
          </div><!-- .col-md-3 -->
        </div><!-- .row -->
      </div>
    </template>
    <div id="comments" class="comments-area">
      <div id="respond" class="comment-respond">
        <h3 id="reply-title" class="comment-reply-title">评论
          <small></small>
        </h3>
        <section class="widget widget_recent_comments">
          <ul id="ul_comments">
            <template>
              <li style="display: list-item;" v-for="item in list"><span class="comment-author-link"><a
                :href="item.website">{{item.userName}}:</a></span>
                <p>{{item.comment}}</p></li>
            </template>
          </ul>
        </section>

        <form id="commentform" class="form-horizontal comment-form">
          <div class="form-group">
            <div class="col-sm-12">
                        <textarea id="comment" class="form-control" rows="6" name="comment" aria-required="true"
                                  placeholder="*内容 :" maxlength="300"></textarea>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-4">
              <input id="userName" name="userName" value="" class="form-control" placeholder="*名字 :"
                     aria-required="true" type="text" maxlength="20">
            </div>
            <div class="col-sm-4">
              <input id="email" name="email" value="" class="form-control" placeholder="*邮箱 :"
                     aria-required="true" type="email" maxlength="50">
              <input name="articleId" value="${article.id}" class="form-control" type="hidden"/>
            </div>
            <div class="col-sm-4">
              <input name="website" type="url" value="" class="form-control" maxlength="100" placeholder="你的网址 :"/>
            </div>
          </div>
          <p class="form-submit">
            <input name="submit" id="submitComments" class="btn btn-danish btn-lg btn-block" value="提交"
                   type="button">
            <input name="comment_post_ID" value="82" id="comment_post_ID" type="hidden">
            <input name="comment_parent" id="comment_parent" value="0" type="hidden">
          </p>
        </form><!-- #commentform -->
      </div><!-- #respond -->
    </div><!-- #comments -->
  </div>
</template>
<script>
  import axios from 'axios'
  export default {
    data () {
      return {article: {}}
    },
    beforeRouteEnter (to, from, next) {
      axios.get('/article/' + to.params.id).then(response => {
        console.log(response)
        next(vm => {
          vm.article = response.data
        })
      })
    }
  }
</script>
