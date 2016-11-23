<#macro page title js=[] css=[] keywords="chulung,chulung's craft,chulung的博客" showHeader="">
<#include "WEB-INF/views/baseMacro.ftl">     
<@base base_title=title mainjs='craft/js/main' base_css=css base_keywords="chulung,chulung's craft,chulung.com" base_showHeader=showHeader>
    <#nested>
<div class="col-md-4">
    <section class="widget widget_search">
        <form role="search" method="get" class="search-form" action="/search">
            <label>
                <span class="screen-reader-text">Search for:</span>
                <input class="search-field" placeholder="CSearch …" value="" name="word" title="Search for:" type="search">
            </label>
            <input class="search-submit" value="Search" type="submit">
        </form><!-- search-form -->
    </section><!-- .widget_search -->

    <section class="widget danish_widget_about">
        <div class="about-author-container">
            <!--  <img src="${assetsRoot}/craft/img/logo.jpg" alt="chulung">-->
            <div class="about-author-info">
                <h2 class="widget-title">chulung</h2>
                <span class="author-subtitle">Developer</span>
                <div class="author-description">
                    <p>目前从事后端开发，关注网站架构，平时也从前端折腾到数据库，感觉正在走向全栈，业余时会下厨，旅行，健身...</p>
                    <a href="/about" class="more-link"><span class="moretext">More</span></a>
                </div><!-- .author-description -->
                <div class="author-footer">
                    <div class="author-social">
                        <a href="https://github.com/chulung" target="_blank" rel="external nofollow" ><i class="fa fa-github"></i></a>
                        <a href="mailto:chulung@chulung.com" rel="external nofollow" target="_blank"><i class="fa fa-envelope"></i></a>
                        <a href="http://weibo.com/chulung" target="_blank" rel="external nofollow" ><i class="fa fa-weibo"></i></a>
                    </div><!-- .author-social -->
                </div><!-- .author-footer -->
            </div><!-- .about-author-info -->
        </div><!-- .about-author-container -->
    </section><!-- .danish_widget_about -->

    <section class="widget danish_widget_popular_entries">
        <h2 class="widget-title">热门</h2>
        <ul id="pop-art-ul">
            <li  class="none">
                <div class="popular-entry-container">
                    <div class="entry-image">
                        <img src="https://static.chulung.com/assets/theme/img/logo.jpg">
                    </div><!-- .entry-image -->
                    <div class="entry-content">
                        <h4 class="entry-title">
                        </h4>
                        <span class="entry-category"><a href=""></a></span>
                        <span class="entry-datetime"></span>
                    </div><!-- .entry-content -->
                </div><!-- .popular-entry-container -->
            </li>
        </ul>
    </section><!-- .danish_widget_popular_entries -->
    <section class="widget widget_recent_comments">
        <h2 class="widget-title">最新评论</h2>
        <ul id="recentcomments">
            <li class="none">
                            </li>
        </ul>
    </section><!-- .widget_recent_comments -->
    <section class="widget widget_archive">
        <h2 class="widget-title">归档</h2>
        <ul id="articleFilings">
        </ul>
    </section><!-- .widget_archive -->

</div><!-- .col-md-4 -->
</@base>
</#macro>