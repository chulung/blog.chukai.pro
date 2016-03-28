package com.wenchukai.util;

import java.io.IOException;

import org.markdown4j.Markdown4jProcessor;

import com.wenchukai.blog.bean.ArticleDraft;

public class MarkdownUtil {
	private static Markdown4jProcessor markdown4jProcessor = new Markdown4jProcessor();

	public static String toHtml(String md) throws IOException {
		return markdown4jProcessor.process(md);
	}
	
	/**
	 * 将草稿中的markdown 文本转化为html文本
	 * @param articleDraft
	 * @return
	 * @throws IOException
	 */
	public static ArticleDraft convertArticleDraft(ArticleDraft articleDraft) throws IOException {
		articleDraft.setContext(toHtml(articleDraft.getContext()));
		return articleDraft;
	}
}
