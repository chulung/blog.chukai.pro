<template>
  <!-- site-footer -->
  <footer id="colophon" class="site-footer"
          style="background-image: url('https://chulung.github.io/assets/theme/img/footer-background.png');"
          role="contentinfo">
    <div class="container">
      <div class="row">
        <div class="col-sm-3">
          <section class="widget widget_tag_cloud">
            <h3 class="widget-title">全部标签</h3>
            <div id="all-tag" class="tagcloud">
              <router-link v-for="item in tags" :to="'/tag?tag=' + item.tagName" key="item.id">
                {{item.tagName}}({{item.count}})
              </router-link>
            </div>
          </section><!-- .widget_tag_cloud -->
        </div><!-- .col-sm-3 -->

        <div class="col-sm-3">
          <section class="widget widget_recent_entries ">
            <h3 class="widget-title">推荐</h3>
            <template v-if="recommendedArticles">
              <ul>
                <li v-for="item in recommendedArticles">
                  <router-link :to="'/article/'+item.uri">{{item.title}}</router-link>
                  <span class="post-date">{{item.createTime}}</span>
                </li>
              </ul>
            </template>
          </section><!-- .widget_recent_entries -->
        </div>

        <div class="col-sm-6">
          <section class="widget danish_widget_site_info">
            <div class="site-info">
              <h3 class="site-brand"><i class="fa fa-user-circle"></i> 初龙.</h3>
              <p>上海</p>
              <p>chulung@chulung.com</p>
            </div><!-- .site-info -->
          </section><!-- .danish_widget_site_info -->
        </div><!-- .col-sm-6 -->
      </div><!-- .row -->
    </div><!-- .container -->
  </footer><!-- #site-footer -->
</template>
<script>
  import axios from '~/plugins/axios'
  export default{
    data () {
      return {tags: {}, recommendedArticles: {}}
    },
    created () {
      axios.get('siteFooterInfo').then((response) => {
        this.tags = response.data.tags
        this.recommendedArticles = response.data.recommendedArticles
      })
    }
  }
</script>
