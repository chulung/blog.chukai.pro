package com.chulung.ciki.processor;

import org.junit.Before;
import org.junit.Test;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.chulung.test.BaseTest;

public class TemplateProcessorTest extends BaseTest {
	@Autowired
	private TemplateProcessor templateProcessor;

	@Before
	public void setUp() {
		System.setProperty("blog.root", "src/test/resources");
	}

	@Test
	public void testProcessor() throws Exception {
		templateProcessor.processor();
	}
	
}
