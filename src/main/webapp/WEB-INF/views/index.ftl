<#include "WEB-INF/views/pageMacro.ftl">
<@page title="首页" showHeader='1' >
<div class="col-md-8 page-default">
    <#list blogs as blog>
    <article class="post format-standard hentry">
        <div class="post-container">
            <div class="post-content">
                <#if blog.pic??>
                    <img src="${blog.pic}">
                </#if>
                <div class="heading-title heading-small">
                    <span class="post-meta-cat"><a href="/blog/page/1?typeId=${blog.typeId}#content">${blog.typeName}</a></span>
                    <h2><a href="/article/${blog.id}" rel="bookmark">${blog.title}</a></h2>
                </div><!-- .heading-small -->

                <div class="post-meta">
                          <span class="posted-on">
                            发表于 <a href="javascript:;" rel="bookmark"> <time class="entry-date" datetime="<@DateTime time=blog.createTime/>"><@DateTime time=blog.createTime/></time> </a>
                          </span><!-- .posted-on -->
                    <span class="byline">
                            by <span class="author vcard"><a class="url fn n" href="javascript:;">${blog.author}</a></span>
                          </span><!-- .byline -->
                    <span class="reading-estimation">${blog.visitCount} 阅读</span>
                </div><!-- .post-meta -->
                <p>${blog.context}.
                    <a href="/article/${blog.id}" class="more-link">
                        <span class="moretext">阅读全文</span> <span class="screen-reader-text">Do not leave your camera when traveling</span>
                    </a><!-- .more-link -->
                </p>
            </div><!-- .post-content -->
        </div><!-- .post-container -->
    </article><!-- .post -->
    </#list>
    <nav class="navigation posts-navigation" role="navigation">
        <h2 class="screen-reader-text">Posts navigation</h2>
        <div class="nav-links">
            <div class="nav-previous"> <a href="<#if prePage?? >/blog/page/${prePage}?typeId=${typeId}</#if>#content"> 前一页</a></div>
            <div class="nav-next">    <a href="<#if nextPage?? >/blog/page/${nextPage}?typeId=${typeId}</#if>#content">后一页</a></div>
        </div>
    </nav><!-- .navigation -->
</div><!-- .col-md-8 -->
</@page> 
