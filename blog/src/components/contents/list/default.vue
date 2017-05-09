<template>
  <div>
  <article class="post format-standard hentry" v-for="article in articles">
    <div class="post-container">
      <div class="post-content">
        <div class="heading-title heading-small">
              <span class="post-meta-cat"><router-link
                :to="'/articles?columnId='+article.columnId+'#content'">{{article.columnName}}</router-link></span>
          <h2>
            <router-link :to="'/article/'+article.uri" rel="bookmark">{{article.title}}</router-link>
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
          <router-link :to="'/article/'+article.uri" class="more-link">
            <span class="moretext">阅读全文</span>
          </router-link><!-- .more-link -->
        </p>
      </div><!-- .post-content -->
    </div><!-- .post-container -->
  </article><!-- .post -->
  </div>
</template>
<script>
  export default{
    props: ['articles']
  }
</script>
