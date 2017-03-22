<template>
  <div class="site-header-affix-wrapper" style="min-height: 90px;">
    <header id="masthead" class="site-header header-dark" role="banner">
      <div class="container">
        <div class="row">
          <div class="col-sm-3">
            <div class="site-branding">
              <!-- //site-title when you use logo image.
              <h1 class="site-title title-image"><a href="index.html" rel="home"><img src="assets/img/danish-image-logo.png" alt="Danish."></a></h1>
              -->
              <h1 class="site-title"><a href="/" rel="home">ChuLung.</a></h1>
              <p class="site-description">Minecraft //</p>
            </div><!-- .site-branding -->
          </div><!-- .col-sm-4 -->

          <div class="col-sm-9">
            <nav id="site-navigation" class="main-navigation" role="navigation">
              <button class="menu-toggle" aria-controls="primary-menu" aria-expanded="false"><i
                class="fa fa-align-left"></i><span class="sr-only">Primary Menu</span></button>

              <div class="menu-testing-menu-container">
                <ul id="primary-menu" class="menu nav-menu" aria-expanded="false">
                  <li class="menu-item active"><router-link to="/">首页</router-link></li>
                  <li class="menu-item menu-item-has-children" aria-haspopup="true"><a href="#">专业</a>
                    <ul class="sub-menu">
                      <li class="menu-item"><router-link to="/column/skills">技能</router-link></li>
                    </ul>
                  </li>
                  <li class="menu-item"><router-link to="/column/sensibility">心悟</router-link></li>
                  <li class="menu-item menu-item-has-children" aria-haspopup="true"><a href="#">其他</a>
                    <ul class="sub-menu">
                      <li class="menu-item"><router-link to="/column/doc">手册</router-link></li>
                      <li class="menu-item"><router-link to="/column/reprints">他山之石</router-link></li>
                    </ul>
                  </li>
                  <li class="menu-item menu-item-has-children" aria-haspopup="true"><a href="#">业余</a>
                    <ul class="sub-menu">
                      <li class="menu-item"><router-link to="/column/cooking ">烹饪</router-link></li>
                      <li class="menu-item"><router-link to="/column/exercise">健身</router-link></li>
                      <li class="menu-item"><router-link to="/column/travel">旅行</router-link></li>
                    </ul>
                  </li>
                  <li class="menu-item menu-item-has-children" aria-haspopup="true"><a href="#">关于</a>
                    <ul class="sub-menu">
                      <li class="menu-item"><router-link to="/about">关于我</router-link></li>
                      <li class="menu-item"><a href="#">简历</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
            </nav><!-- #site-navigation -->
          </div><!-- .col-sm-8 -->
        </div><!-- .row -->
      </div><!-- .container -->
    </header><!-- #masthead -->
  </div><!-- .site-header-affix-wrapper -->

</template>
<script>
  export default{
    data: function () {
      return {}
    }
  }
</script>
