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
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chulung.blog.mapper.CikiMapper;
import com.chulung.blog.model.BaseComponent;
import com.chulung.blog.model.Ciki;

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
	private CikiMapper cikiMapper;
	private Configuration cfg = new Configuration(new Version("2.3.23"));
	private PegDownProcessor downProcessor = new PegDownProcessor();

	public TemplateProcessor() throws IOException {
		cfg.setDirectoryForTemplateLoading(new File(getClass().getResource("/com/chulung/ciki/templates/").getPath()));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setNumberFormat("#");
	}

	public void processor() throws Exception {
		generateCiki(this.getCikisByParentId(0));
	}

	private void generateCiki(List<Ciki> cikis) throws Exception {
		String rootPath = System.getProperty("blog.root");
		File file = new File(rootPath + "/ciki");
		FileUtils.deleteDirectory(file);
		file.mkdir();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("cikis", cikis);
		printTemplate(hashMap, "indexPage.ftl", file.getPath() + "/index.html");
		cikis.forEach(c -> {
			File file2 = new File(file.getPath() + "/" + c.getTitle());
			file2.mkdir();
			c.getCikis().forEach(a -> {
				if (a.getType() == 1) {
					try {
						HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
						hashMap2.put("article", a);
						hashMap2.put("html", downProcessor.markdownToHtml(a.getMarkdown()));
						printTemplate(hashMap2, "contextPage.ftl", file2.getPath() + "/" + a.getTitle() + ".html");
					} catch (Exception e) {
						this.errorLog(e);
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

	private List<Ciki> getCikisByParentId(Integer parentId) {
		Ciki record = new Ciki();
		record.setParentId(parentId);
		List<Ciki> categories = this.cikiMapper.select(record);
		categories.stream().parallel().forEach(c -> {
			c.setCikis(this.getCikisByParentId(c.getId()));
		});
		return categories;
	}

}
