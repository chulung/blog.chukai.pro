<template>
  <div id="content" class="site-content">
    <div id="primary" class="content-area">
      <main id="main" class="site-main" role="main">
        <div class="container">
          <div class="row">
              <div class="col-md-8 ">
                <router-view></router-view>
              </div>
            <sidebar></sidebar>
          </div><!-- .row -->
        </div><!-- .container -->
      </main><!-- #main -->
    </div><!-- #primary -->
  </div>
</template>
<script>
  import sidebar from './sidebar.vue'
  export default{
    components: {sidebar}
  }
</script>
