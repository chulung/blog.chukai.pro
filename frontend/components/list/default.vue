<template>
  <div>
    <header class="page-header" v-show="this.$route.path==='/search' && articles && articles.length">
      <h1 class="page-title high-light">搜索结果: <em>{{this.$route.query.word}}</em></h1>
    </header>
    <header class="page-header" v-show="articles && !articles.length">
      <h1 class="page-title high-light" v-html="info()"></h1>
    </header>
    <article class="post format-standard hentry" v-for="article in articles">
      <div class="post-container">
        <div class="post-content">
          <div class="heading-title heading-small">
              <span class="post-meta-cat"><router-link
                :to="'/articles?columnId='+article.columnId+'#content'">{{article.columnName}}</router-link></span>
            <h2>
              <router-link :to="'/article/'+article.uri" rel="bookmark" v-html="article.title"
                           class="high-light"></router-link>
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
          <p v-html="article.summary" class="high-light">
          </p>
          <router-link :to="'/article/'+article.uri" class="more-link">
            <span class="moretext">阅读全文</span>
          </router-link><!-- .more-link -->
        </div><!-- .post-content -->
      </div><!-- .post-container -->
    </article><!-- .post -->

  </div>
</template>
<script>
  export default{
    props: ['articles'],
    methods: {
      info () {
        return this.$route.path === '/search' ? `没搜索到包含<em>${this.$route.query.word}</em>的文章。` : '当前目录还没有文章。_(:з」∠)_'
      }
    }
  }
</script>
<style>
  .high-light em {
    color: #ff0000;
    font-style: normal;
    font-weight: 100;
  }
</style>
