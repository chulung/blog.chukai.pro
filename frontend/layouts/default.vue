<template>
  <div class="site">
    <navigation></navigation>
    <!-- content -->
    <div id="content" class="site-content">
      <div id="primary" class="content-area">
        <main id="main" class="site-main" role="main">
          <div class="container">
            <div class="row">
              <div class="col-md-8 ">
                <nuxt/>
              </div>
              <sidebar></sidebar>
            </div><!-- .row -->
          </div><!-- .container -->
        </main><!-- #main -->
      </div><!-- #primary -->
    </div>
    <!-- #content -->
    <sitefooter></sitefooter>
    <copyright></copyright>
  </div><!-- #page -->
</template>

<script>
  import navigation from '../components/navigation.vue'
  import sitefooter from '../components/sitefooter.vue'
  import copyright from '../components/copyright.vue'
  import sidebar from '../components/sidebar.vue'

  export default {
    components: {
      navigation, sitefooter, copyright, sidebar
    },
    head: {
      title: '首页',
      script: [
        {src: 'https://apps.bdimg.com/libs/jquery/1.11.3/jquery.min.js'},
        {src: 'https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js'},
        {src: '/js/lib/script.js'}
      ],
      link: [
        {rel: 'stylesheet', href: 'https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css'},
        {rel: 'stylesheet', href: 'https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css'},
        {rel: 'stylesheet', href: '/css/site.css'}
      ]
    }
  }
</script>
