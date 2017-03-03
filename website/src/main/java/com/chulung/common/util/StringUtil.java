package com.chulung.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chulung.website.dto.Node;

public class StringUtil extends org.apache.commons.lang3.StringUtils {
	public static String toLowerCaseFirstChar(String s) {
		char[] arr = s.toCharArray();
		if (arr[0] >= 'A' && arr[0] <= 'Z') {
			arr[0] += 32;
		}
		return String.valueOf(arr);
	}

	/**
	 * 为所有h标签添加id，用于页面锚点
	 * 
	 * @param htmlContext
	 * @return
	 */
	public static String handlerHTagIndex(String htmlContext) {
		List<String> hTags = getHTagList(htmlContext);
		List<String> tmp = new ArrayList<>(hTags);
		Node<String> root = new Node<String>(0, null, null, new LinkedList<Node<String>>());
		resolveHTagNode(root, hTags.listIterator());
		Iterator<String> iter = hTags.iterator();
		for (String string : tmp) {
			htmlContext = htmlContext.replace(string, iter.next());
		}
		return htmlContext;
	}

	public static Node<String> getCatalog(String htmlConText) {
		Node<String> root = new Node<String>(0, null, null, new LinkedList<Node<String>>());
		resolveHTagNode(root, getHTagList(htmlConText).listIterator());
		return root;
	}

	public static List<String> getHTagList(String htmlContext) {
		Pattern pattern = Pattern.compile("<h[1-6]{1}>.*?<h[1-6]{1}>");
		Matcher matcher = pattern.matcher(htmlContext);
		List<String> hTags = new LinkedList<>();
		while (matcher.find()) {
			hTags.add(matcher.group());
		}
		return hTags;
	}

	/**
	 * 获取h标签生成的目录节点
	 * 
	 * @param root
	 * @param iterator
	 * @return root
	 */
	public static Node<String> resolveHTagNode(Node<String> root, ListIterator<String> iterator) {
		Node<String> curNode = null;
		while (iterator.hasNext()) {
			String h = iterator.next();
			int depth = getHTagIndex(h);
			if (depth < root.getDepth() + 1) {
				iterator.previous();
				return root;
			} else if (depth == root.getDepth() + 1) {
				String id = "htag" + iterator.nextIndex();
				curNode = new Node<String>(depth, root, id, new LinkedList<Node<String>>());
				iterator.set(h.substring(0, 3) + " id=\"" + id + "\"" + h.substring(3));
				root.getChildNodes().add(curNode);
			} else {
				iterator.previous();
				resolveHTagNode(curNode, iterator);
			}

		}
		return root;
	}

	public static int getHTagIndex(String h) {
		return h.charAt(2) - 48;
	}
}
