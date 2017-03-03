package com.chulung.common.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.chulung.website.dto.Node;

public class StringUtilTest {

	@Test
	public void testResolveNode() {
		Node<String> root = new Node<String>(0, null, null, new LinkedList<Node<String>>());
		List<String> hTags = new ArrayList<>();
		hTags.add("<h1></h1>");
		hTags.add("<h2></h2>");
		hTags.add("<h2></h2>");
		hTags.add("<h1></h1>");
		hTags.add("<h2></h2>");
		hTags.add("<h3></h3>");
		hTags.add("<h1></h1>");
		hTags.add("<h2></h2>");
		StringUtil.resolveHTagNode(root, hTags.listIterator());
		assertEquals(root.getChildNodes().size(), 3);
	}

	@Test
	public void testGetHTagIndex() {
		for (int i = 1; i < 7; i++) {
			assertEquals(i, StringUtil.getHTagIndex("<h" + i));
		}
	}

}
