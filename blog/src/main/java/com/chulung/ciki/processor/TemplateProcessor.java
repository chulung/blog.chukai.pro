package com.chulung.ciki.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chulung.blog.mapper.AppLogMapper;
import com.chulung.blog.mapper.ArticleMapper;
import com.chulung.blog.mapper.CategoryMapper;
import com.chulung.blog.model.Category;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

@Component
public class TemplateProcessor {
	@Autowired
	private CategoryMapper CategoryMapper;
	private AppLogMapper appLogMapper;
	@Autowired
	private ArticleMapper articleMapper;
	private Configuration cfg = new Configuration(new Version("2.3.23"));

	public TemplateProcessor() {
		cfg.setDefaultEncoding("UTF-8");
		cfg.setNumberFormat("#");
	}

	public void processor() {
		Category category = new Category();
		category.setCategoryName("Ciki");
		category.setCategoryType("CIKI");
		category = this.CategoryMapper.selectOne(category);
		generateCiki(this.getCategoryByParentId(category.getId()));
	}

	private void generateCiki(List<Category> categories) {
		String webappPath = System.getProperty("blog.root");
		File file = new File(webappPath + "/ciki");
		try {
			FileUtils.deleteDirectory(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		file.mkdir();

		try {
			String templatePath = "com/chulung/ciki/templates/indexPage.ftl";
			String outputPath = file.getPath() + "/index.html";
			printTemplate(categories, templatePath, outputPath);
			categories.forEach(c -> {
				File file2 = new File(file.getPath() + "/" + c.getCategoryName());
				file2.mkdir();
				c.getCategories().forEach(a -> {

				});
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printTemplate(Object data, String templatePath, String outputPath)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			UnsupportedEncodingException, FileNotFoundException, TemplateException {
		Template template = cfg.getTemplate(templatePath);
		Writer out = new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8");
		template.process(data, out);
	}

	private List<Category> getCategoryByParentId(Integer parentId) {
		Category record = new Category();
		record.setParentId(parentId);
		List<Category> categories = this.CategoryMapper.select(record);
		categories.stream().parallel().forEach(c -> {
			c.setCategories(this.getCategoryByParentId(c.getId()));
		});
		return categories;
	}

}
