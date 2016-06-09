package com.chulung.ciki.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chulung.blog.mapper.ArticleMapper;
import com.chulung.blog.mapper.CategoryMapper;
import com.chulung.blog.model.Article;
import com.chulung.blog.model.BaseComponent;
import com.chulung.blog.model.Category;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

@Component
public class TemplateProcessor extends BaseComponent {
	@Autowired
	private CategoryMapper CategoryMapper;
	@Autowired
	private ArticleMapper articleMapper;
	private Configuration cfg = new Configuration(new Version("2.3.23"));

	public TemplateProcessor() throws IOException {
		cfg.setDirectoryForTemplateLoading(new File(getClass().getResource("/com/chulung/ciki/templates/").getPath()));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setNumberFormat("#");
	}

	public void processor() throws Exception {
		Category category = new Category();
		category.setCategoryName("Ciki");
		category.setCategoryType("CIKI");
		category = this.CategoryMapper.selectOne(category);
		generateCiki(this.getCategoryByParentId(category.getId()));
	}

	private void generateCiki(List<Category> categories) throws Exception {
		String rootPath = System.getProperty("blog.root");
		File file = new File(rootPath + "/ciki");
		FileUtils.deleteDirectory(file);
		file.mkdir();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("categories", categories);
		printTemplate(hashMap, "indexPage.ftl", file.getPath() + "/index.html");
		categories.forEach(c -> {
			File file2 = new File(file.getPath() + "/" + c.getCategoryName());
			file2.mkdir();
			c.getCategories().forEach(a -> {
				if (a.getArticleId() != null) {
					Article record = new Article();
					record.setId(a.getArticleId());
					Article article = this.articleMapper.selectOne(record);
					if (article != null) {
						try {
							HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
							hashMap2.put("article", article);
							printTemplate(hashMap2, "contextPage.ftl",
									file2.getPath() + "/" + article.getTitle() + ".html");
						} catch (Exception e) {
							this.errorLog(e);
						}
					}
				}
			});
		});
	}

	private void printTemplate(Map<String, Object> data, String templateName, String outputPath)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			UnsupportedEncodingException, FileNotFoundException, TemplateException {
		Template template = cfg.getTemplate(templateName);
		Writer out = new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8");
		template.process(data, out);
		out.flush();
		out.close();
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
