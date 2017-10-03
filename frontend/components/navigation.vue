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
              <h1 class="site-title">
                <router-link to="/" rel="home">初开.</router-link>
              </h1>
              <p class="site-description">Minecraft //</p>
            </div><!-- .site-branding -->
          </div><!-- .col-sm-4 -->

          <div class="col-sm-9">
            <nav id="site-navigation" class="main-navigation" role="navigation">
              <button class="menu-toggle" aria-controls="primary-menu" aria-expanded="false"><i
                class="fa fa-align-left"></i><span class="sr-only">Primary Menu</span></button>

              <div class="menu-testing-menu-container">
                <ul id="primary-menu" class="menu nav-menu" aria-expanded="false">
                  <li class="menu-item active">
                    <router-link to="/">首页</router-link>
                  </li>
                  <li class="menu-item">
                    <router-link to="/column/depth">深度</router-link>
                  </li>
                  <li class="menu-item">
                    <router-link to="/column/breadth">广度</router-link>
                  </li>
                  <li class="menu-item">
                    <router-link to="/column/cognition">认知</router-link>
                  </li>
                  <li class="menu-item">
                    <router-link to="/column/amateur">生活</router-link>
                  </li>
                  <li class="menu-item menu-item-has-children" aria-haspopup="true"><a href="#">关于</a>
                    <ul class="sub-menu">
                      <li class="menu-item">
                        <router-link to="/article/about">关于我</router-link>
                      </li>
                      <li class="menu-item"><a href="#"></a></li>
                      <!--<li class="menu-item"><a href="#">知识体系</a></li>-->
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
    },
    mounted () {
      this.initNavigation()
    },
    methods: {
      initNavigation: function () {
        var container, button, menu, links, subMenus, i, len
        container = document.getElementById('site-navigation')
        if (!container) {
          return
        }

        button = container.getElementsByTagName('button')[0]
        if (typeof button === 'undefined') {
          return
        }
        menu = container.getElementsByTagName('ul')[0]

        // Hide menu toggle button if menu is empty and return early.
        if (typeof menu === 'undefined') {
          button.style.display = 'none'
          return
        }

        menu.setAttribute('aria-expanded', 'false')
        if (menu.className.indexOf('nav-menu') === -1) {
          menu.className += ' nav-menu'
        }

        button.onclick = function () {
          if (container.className.indexOf('toggled') !== -1) {
            container.className = container.className.replace(' toggled', '')
            button.setAttribute('aria-expanded', 'false')
            menu.setAttribute('aria-expanded', 'false')
          } else {
            container.className += ' toggled'
            button.setAttribute('aria-expanded', 'true')
            menu.setAttribute('aria-expanded', 'true')
          }
        }

        // Get all the link elements within the menu
        links = menu.getElementsByTagName('a')
        subMenus = menu.getElementsByTagName('ul')

        // Set menu items with submenus to aria-haspopup="true".
        for (i = 0, len = subMenus.length; i < len; i++) {
          subMenus[i].parentNode.setAttribute('aria-haspopup', 'true')
        }

        // Each time a menu link is focused or blurred, toggle focus.
        for (i = 0, len = links.length; i < len; i++) {
          links[i].addEventListener('focus', toggleFocus, true)
          links[i].addEventListener('blur', toggleFocus, true)
        }
        function toggleFocus () {
          var self = this
          // Move up through the ancestors of the current link until we hit .nav-menu.
          while (self.className.indexOf('nav-menu') === -1) {
            // On li elements toggle the class .focus.
            if (self.tagName.toLowerCase() === 'li') {
              if (self.className.indexOf('focus') !== -1) {
                self.className = self.className.replace(' focus', '')
              } else {
                self.className += ' focus'
              }
            }
            self = self.parentElement
          }
        }
      },
      hideNav: function () {
        var container = document.getElementById('site-navigation')
        container.className = container.className.replace(' toggled', '')
      }
    }
  }
</script>
