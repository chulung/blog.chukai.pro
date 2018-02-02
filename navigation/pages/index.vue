<template>
  <div id="fullpage">
    <div class="section">Some section</div>
    <div class="section">Some section</div>
    <div class="section">Some section</div>
    <div class="section">Some section</div>
  </div>
</template>

<script>
  import AppLogo from '~/components/AppLogo.vue'

  export default {

    head: {
      title: '首页',
      script: [
        {src: 'https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js'},
        {src: 'https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js'},
        {src: 'https://cdn.bootcss.com/fullPage.js/2.9.5/jquery.fullpage.min.js'}
      ],
      link: [
        {rel: 'stylesheet', href: 'https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css'},
        {rel: 'stylesheet', href: 'https://cdn.bootcss.com/fullPage.js/2.9.5/jquery.fullpage.min.css'}
      ]
    },
    components: {
      AppLogo
    },
    mounted(){
      $(document).ready(function () {
        $('#fullpage').fullpage();
      });
    }
  }
</script>

