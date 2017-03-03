package com.chulung.freemaker.template;


import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by chukai on 2017/2/25.
 */
public class EncodeURLMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        try {
            String encode = URLEncoder.encode(arguments.get(0).toString(), "utf-8");
            return encode;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
