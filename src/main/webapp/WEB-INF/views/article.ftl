<#include "WEB-INF/views/pageMacro.ftl">     
<@page title=article.title >
<div class="col-md-8">
    <article class="post format-standard hentry" >
        <header class="entry-header">
            <div class="heading-title">
                <h1 class="entry-title">${article.title}</h1>
            </div>
            <div class="entry-meta">
                <span class="posted-on">发表于 <@DateTime time=article.createTime/></span>
                <span class="byline"> by <span class="author vcard"><a class="url fn n" href="javascript:;">${article.author}</a></span></span>
                <span class="meta-viewer">${article.visitCount}阅读</span>
                <span class="reading-estimation">${blog.commentCount}评论</span>
            </div><!-- .entry-meta -->
        </header><!-- .entry-header -->

        <div class="entry-content">
			${article.context}
        </div><!-- .entry-content -->

        <footer class="entry-footer">
            <span class="cat-links">发表在 <a href="https://chulung.com">chulung's craft</a></span><span class="tags-links">标签 <a href="#">#</a>, <a href="#">#</a>, <a href="#">#</a></span>
        </footer><!-- .entry-footer -->
    </article><!-- .post -->

    <div id="comments" class="comments-area">
        <div id="respond" class="comment-respond">
            <h3 id="reply-title" class="comment-reply-title">评论 <small></small></h3>
            <form action="" method="" id="commentform" class="form-horizontal comment-form" >
                <div class="form-group">
                    <div class="col-sm-12">
                        <textarea id="comment" class="form-control" rows="6" name="comment" aria-required="true" placeholder="*内容 :" maxlength="300"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <input id="userName" name="userName" value="" class="form-control" placeholder="*名字 :" aria-required="true" type="text" maxlength="20">
                    </div>
                    <div class="col-sm-4">
                        <input name="email" value="" class="form-control" placeholder="邮箱(选填) :" aria-required="true" type="email" maxlength="50">
                        <input name="articleId" value="${article.id}" class="form-control" type="hidden"/>
                    </div>
                </div>
                <p class="form-submit">
                    <input name="submit" id="submitComments" class="btn btn-danish btn-lg btn-block" value="提交" type="button">
                    <input name="comment_post_ID" value="82" id="comment_post_ID" type="hidden">
                    <input name="comment_parent" id="comment_parent" value="0" type="hidden">
                </p>
            </form><!-- #commentform -->
        </div><!-- #respond -->
    </div><!-- #comments -->
</div>
</@page>