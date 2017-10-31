<template>
  <!-- copyright -->
  <section id="copyright" class="copyright" :class="{none:none}">
    <div class="container">
      <div class="row">
        <div class="col-sm-6 copy-left">
          Copyright © 2015-2017 <a href="/"><strong>初开</strong></a>. All Right Reserved.
        </div><!-- .col-sm-6 -->
        <div class="col-sm-6 copy-right">
          <ul class="social-icon">
            <li><a href="https://github.com/wchukai/wchukai.com" target="_blank" class="icon-twitter"
                   rel="external nofollow"><i class="fa fa-github"></i></a></li>
            <li><a href="mailto:chukai@wchukai.com" target="_blank" class="icon-instagram" rel="external nofollow"><i
              class="fa fa-envelope"></i></a></li>
          </ul>
        </div><!-- .col-sm-6 -->
      </div><!-- .row -->
    </div><!-- .container -->
  </section><!-- #copyright -->
</template>
<script>
  export default{
    data () {
      return {none: true}
    },
    created () {
      setTimeout(() => {
        this.none = false
      }, 2000)
    }
  }
</script>
