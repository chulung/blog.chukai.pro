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
        <div class="nav-previous"><a
          href="<#if prePage?? >/articles?page=${prePage}&typeId=<#if column??>${column.id}</#if></#if>#content">
          前一页</a></div>
        <div class="nav-next"><a
          href="<#if nextPage?? >/articles?page=${nextPage}&typeId=<#if column??>${column.id}</#if></#if>#content">后一页</a>
        </div>
      </div>
    </nav><!-- .navigation -->
  </div><!-- .col-md-8 -->
</template>
<script>
  export default{
    data: function () {
      return {articles: []}
    },
    created: function () {
      for (let i = 0; i < 10; i++) {
        this.articles.push({'pic': '/static/logo.png'})
      }
    }
  }

</script>
