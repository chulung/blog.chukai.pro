package com.wenchukai.freemaker.template;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class DateTimeTemplate implements TemplateDirectiveModel {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Object paramValue = params.get("time");
		String date = paramValue==null?"":paramValue.toString();
		Writer out = env.getOut();
		out.write(date.replace('T', ' '));
		out.flush();
	}

}
