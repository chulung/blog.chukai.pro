package com.chulung.freemaker.template;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.time.LocalDateTime;
import java.util.List;

public class DateTimeMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return arguments.get(0).toString().replace('T', ' ');
    }
}
