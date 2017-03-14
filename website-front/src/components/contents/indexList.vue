<template>
  <div class="col-md-9 md-margin-bottom-50">
    <template v-if="articles">
      <div class="row margin-bottom-50" v-for="item in articles">
        <div class="col-sm-4 sm-margin-bottom-20">
          <img class="img-responsive" :src="item.pic" alt="">
        </div>
        <div class="col-sm-8">
          <div class="blog-grid">
            <h3><a href="blog_single.html">New winter fashion style</a></h3>
            <ul class="blog-grid-info">
              <li>Evan Bartlett</li>
              <li>Mar 6, 2015</li>
              <li><a href="#"><i class="fa fa-comments"></i> 0</a></li>
            </ul>
            <p>Pellentesque turpis lacus, tempus et fermentum vitae, dignissim ornare purus. Nulla facilisi.
              Suspendisse potenti. Aenean vitae lacus lobortis lacus finibus volutpat eu nec sem. Sed ultrices velit
              vitae tortor posuere ultrices.</p>
            <a class="r-more" href="blog_single.html">Read More</a>
          </div>
        </div>
      </div>
    </template>
    <!-- Pager v4 -->
    <ul class="pager pager-v4">
      <li class="previous"><a class="rounded-3x" href="#">← Older</a></li>
      <li class="page-amount">1 of 7</li>
      <li class="next"><a class="rounded-3x" href="#">Newer →</a></li>
    </ul>
    <!-- End Pager v4 -->
  </div>
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
      console.log(this.articles)
    }
  }

</script>
