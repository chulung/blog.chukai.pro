<template>
  <div id="app">
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                  data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <router-link class="navbar-brand" to="/cms">HOME</router-link>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li><router-link to="/cms/drafts"><i class="glyphicon glyphicon-list"></i>草稿列表</router-link></li>
            <li><router-link to="/cms/editor"><i class="glyphicon glyphicon-list"></i>草稿编辑</router-link></li>
          </ul>
          </form>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    <nuxt/>
  </div><!-- #page -->
</template>

<script>
  export default {
    head: {
      title: 'CMS',
      script: [
        {src: 'https://apps.bdimg.com/libs/jquery/1.11.3/jquery.min.js'},
        {src: 'https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js'}
      ],
      link: [
        {rel: 'stylesheet', href: 'https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css'},
        {rel: 'stylesheet', href: 'https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css'}
      ]
    }
  }
</script>
