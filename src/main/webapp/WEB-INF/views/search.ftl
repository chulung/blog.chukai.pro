<#include "WEB-INF/views/pageMacro.ftl">
<@page title="首页" showHeader='1' >
<div class="col-md-8">
    <header class="page-header">
        <h1 class="page-title">搜索结果: ${word}</h1>
    </header>

    <div class="row row_search entry-container">
        <div class="col-md-12 ">
            <#list articles as article>
            <article class="post format-standard hentry entry-list">
                <div class="row">
                    <div>
                        <div class="entry-list-title">
                            <h2 class="entry-title"><a href="/article/${article.id!}" rel="bookmark">${article.title!}</a></h2>
                        </div><!-- .entry-list-title -->

                        <div class="entry-summary">
                            <p>
                                 ${article.context!}
                                <a href="/article/${article.id!}" class="more-link">
                                    <span class="moretext">阅读全文</span>
                                </a>
                            </p>
                        </div><!-- .entry-summary -->
                    </div><!-- .col-# -->
                </div><!-- .entry-list -->
            </article><!-- .post -->
            </#list>

        </div><!-- .col-md-12 -->
    </div><!-- .entry-container -->
</div>
</@page>
