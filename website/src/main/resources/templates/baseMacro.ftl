<#compress>
<#macro base base_title mainjs="" base_css=[] base_keywords="" base_showHeader="" >
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="chulung's craft">
    <meta name="keywords" content="${base_keywords}">
    <meta name="author" content="chulung">
    <link rel="shortcut icon"	href="/favicon.ico"	type="image/x-icon" />
    <title>${base_title}-chulung's craft</title>

    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/magnific-popup.js/1.0.1/magnific-popup.min.css" rel="stylesheet">
    <link href="/assets/website/css/style.min.css" rel="stylesheet">
    <#list base_css as c>
        <link href="/${c}" rel="stylesheet">
    </#list>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript"  src="https://apps.bdimg.com/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>
        $(function(){
            //cdn可能导致图片加载失败，访问原站
            $("img").on("error",function () {
                $(this).attr("src",$(this).attr("src").replace('static.',''))
                $(this).off("error");
            });
        })
    </script>
</head>
<body class="home blog">
<div id="page" class="site">
    <a class="skip-link screen-reader-text" href="#content">Skip to content</a>
    <div class="site-header-affix-wrapper" style="min-height: 90px;">
        <header id="masthead" class="site-header header-dark" role="banner">
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="site-branding">
                            <!-- //site-title when you use logo image.
                            <h1 class="site-title title-image"><a href="index.html" rel="home"><img src="assets/img/danish-image-logo.png" alt="Danish."></a></h1>
                            -->
                            <h1 class="site-title"><a href="/" rel="home">chulung.</a></h1>
                            <!-- //site-description if you wanna use it.
                            <p class="site-description">Traveler and Young Designer //</p>
                            -->
                        </div><!-- .site-branding -->
                    </div><!-- .col-sm-4 -->

                    <div class="col-sm-9">
                        <nav id="site-navigation" class="main-navigation" role="navigation">
                            <button class="menu-toggle" aria-controls="primary-menu" aria-expanded="false"><i class="fa fa-align-left"></i><span class="sr-only">Primary Menu</span></button>

                            <div class="menu-testing-menu-container">
                                <ul id="primary-menu" class="menu nav-menu" aria-expanded="false">
                                    <li class="menu-item"><a href="/">首页</a></li>
                                    <li class="menu-item"><a href="/column/skills">技能</a></li>
                                    <li class="menu-item"><a href="/column/sensibility">心悟</a></li>
                                    <li class="menu-item menu-item-has-children" aria-haspopup="true"><a href="#">其他</a>
                                        <ul class="sub-menu">
                                            <li class="menu-item"><a href="/column/doc">手册</a></li>
                                            <li class="menu-item"><a href="/column/reprints">他山之石</a></li>
                                        </ul>
                                    </li>
                                    <li class="menu-item menu-item-has-children" aria-haspopup="true"><a href="#">业余</a>
                                        <ul class="sub-menu">
                                            <li class="menu-item"><a href="/column/cooking ">烹饪</a></li>
                                            <li class="menu-item"><a href="/column/exercise">健身</a></li>
                                            <li class="menu-item"><a href="/column/travel">旅行</a></li>
                                        </ul>
                                    </li>
                                    <li class="menu-item menu-item-has-children" aria-haspopup="true"><a href="#">关于</a>
                                        <ul class="sub-menu">
                                            <li class="menu-item"><a href="/about">关于我</a></li>
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

    <#if base_showHeader=='1'>

        <!-- #header -->
        <section id="header" style="background-image: url('//ww1.sinaimg.cn/mw690/967a8004gw1fa2f8npjnkj20sg0g0juy.jpg');">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <blockquote>
                            <p><#if column?? && column.slogans??>${column.slogans}<#else>This is chulung's craft</#if></p>
                            <small>chulung</small>
                        </blockquote>
                    </div>
                </div>
            </div>
            <div class="overlay"></div>
        </section><!-- #header -->
    </#if>
    <!-- content -->
    <div id="content" class="site-content">
        <div id="primary" class="content-area">
            <main id="main" class="site-main" role="main">

                <div class="container">
                    <div class="row">

                        <#nested>

                    </div><!-- .row -->
                </div><!-- .container -->

            </main><!-- #main -->
        </div><!-- #primary -->
    </div><!-- #content -->

    <!-- site-footer -->
    <footer id="colophon" class="site-footer" style="background-image: url('https://chulung.github.io/assets/theme/img/footer-background.png');" role="contentinfo">
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <section class="widget widget_tag_cloud">
                        <h3 class="widget-title">全部标签</h3>
                        <div id="all-tag" class="tagcloud">
                            <template>
                                <a v-for="item in tags" :href="'/tag/' + item.tagName">{{item.tagName}}({{item.count}})</a>
                            </template>
                        </div>
                    </section><!-- .widget_tag_cloud -->
                </div><!-- .col-sm-3 -->

                <div class="col-sm-3">
                    <section class="widget widget_recent_entries ">
                        <h3 class="widget-title">推荐</h3>
                        <template v-if="recommendedArticles">
                        <ul>
                            <li v-for="item in recommendedArticles">
                                <a :href="'/article/'+item.id">{{item.title}}</a>
                                <span class="post-date">{{item.createTime}}</span>
                            </li>
                        </ul>
                        </template>
                    </section><!-- .widget_recent_entries -->
                </div>

                <div class="col-sm-6">
                    <section class="widget danish_widget_site_info">
                        <div class="site-info">
                            <h3 class="site-brand"><i class="fa fa-user-circle"></i> chulung.</h3>
                            <p>上海</p>
                            <p>chulung@chulung.com</p>
                        </div><!-- .site-info -->
                    </section><!-- .danish_widget_site_info -->
                </div><!-- .col-sm-6 -->
            </div><!-- .row -->
        </div><!-- .container -->
    </footer><!-- #site-footer -->

    <!-- copyright -->
    <section id="copyright" class="copyright">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 copy-left">
                    Copyright © 2015-2017 <a href="/"><strong>chulung</strong></a>. All Right Reserved.
                </div><!-- .col-sm-6 -->
                <div class="col-sm-6 copy-right">
                    <ul class="social-icon">
                        <li><a href="https://github.com/chulung/chulung.com" target="_blank" class="icon-twitter"  rel="external nofollow" ><i class="fa fa-github"></i></a></li>
                        <li><a href="mailto:chulung@chulung.com" target="_blank" class="icon-instagram"  rel="external nofollow" ><i class="fa fa-envelope"></i></a></li>
                        <li><a href="http://weibo.com/chulung" target="_blank" class="icon-instagram"  rel="external nofollow" ><i class="fa fa-weibo"></i></a></li>
                    </ul>
                </div><!-- .col-sm-6 -->
            </div><!-- .row -->
        </div><!-- .container -->
    </section><!-- #copyright -->

</div><!-- #page -->
<script>var module="${moduleName!''}"</script>
<script type="text/javascript"  src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/vue/2.1.10/vue.min.js"></script>
<script type="text/javascript"  src="//cdn.bootcss.com/justifiedGallery/3.6.1/js/jquery.justifiedGallery.js"></script>
<script type="text/javascript"  src="//cdn.bootcss.com/magnific-popup.js/1.0.1/jquery.magnific-popup.min.js"></script>
<script type="text/javascript"  src="//cdn.bootcss.com/jquery.imagesloaded/3.1.8/imagesloaded.pkgd.min.js"></script>
<script type="text/javascript"  src="//cdn.bootcss.com/jquery.isotope/1.5.25/jquery.isotope.min.js"></script>
<script type="text/javascript"  src="//cdn.bootcss.com/masonry/3.1.5/masonry.pkgd.min.js"></script>
<script type="text/javascript"  src="/assets/website/js/lib/navigation.js"></script>
<script type="text/javascript"  src="/assets/website/js/lib/skip-link-focus-fix.js"></script>
<script type="text/javascript"  src="/assets/website/js/lib/script.js"></script>
<script data-main='/assets${mainjs}.js' src="https://cdn.bootcss.com/require.js/2.2.0/require.min.js"></script>
<script>
(function(){
    var e = /([http|https]:\/\/chulung\.com)/gi;
    if (e.test(window.location.href)) {
        var bp = document.createElement('script');
        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(bp, s);
    }
})();
</script>
</body>
</html>
</#macro>
</#compress>