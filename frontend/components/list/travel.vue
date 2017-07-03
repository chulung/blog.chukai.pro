<template>
  <div class="row container-post">
    <div class="col-sm-6 col-xs-12" v-for="article in articles">
      <article class="post format-standard hentry post-type-portfolio">
        <div class="post-container">
          <div class="post-content">
            <img src="danish-image-portfolio-02.jpg" alt="Ferris wheel">
            <div class="heading-title heading-small">
              <span class="post-meta-cat"><a href="archive.html">photography</a></span>
              <h2><a href="single-portfolio.html" rel="bookmark">Ferris wheel //</a></h2>
            </div><!-- .heading-title -->
          </div><!-- .post-content -->
        </div>
      </article><!-- #post-## -->
    </div><!-- .col-sm-6 -->

  </div>
</template>
<script>
  export default{
    props: ['articles']
  }
</script>
