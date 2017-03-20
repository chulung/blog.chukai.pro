<template>
  <div class="col-md-8 page-default">
    <article class="post format-standard hentry" v-for="article in articles">
      <div class="post-container">
        <div class="post-content">
          <template v-if="article.pic">
            <img :src="article.pic">
          </template>
          <div class="heading-title heading-small">
              <span class="post-meta-cat"><a
                :href="'/articles?typeId='+article.typeId+'#content'">{{article.typeName}}</a></span>
            <h2><a href="'/article/'+article.id" rel="bookmark">{{article.title}}</a></h2>
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
          <p>{{article.summary}}.
            <a :href="'/article/'+article.id" class="more-link">
              <span class="moretext">阅读全文</span>
            </a><!-- .more-link -->
          </p>
        </div><!-- .post-content -->
      </div><!-- .post-container -->
    </article><!-- .post -->
    <nav class="navigation posts-navigation" role="navigation">
      <h2 class="screen-reader-text">Posts navigation</h2>
      <div class="nav-links">
        <div class="nav-previous" v-if="prePage">
          <a :href="'/articles?page='+prePage+'&typeId='+typeId">前一页</a>
        </div>
        <div class="nav-next" v-if="nextPage">
          <a :href="'/articles?page='+nextPage+'&typeId='+typeId">前一页</a>
        </div>
      </div>
    </nav><!-- .navigation -->
  </div><!-- .col-md-8 -->
</template>
<script>
  export default{
    data: function () {
      return {articles: {}, nextPage: null, prePage: null}
    },
    created: function () {
      this.ajax.get('https://blog.chulung.com/articles').then(response => {
        this.articles = response.data.list
        this.nextPage = response.data.netxPage
        this.prePage = response.data.prePage
      })
    }
  }

</script>
