/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.chulung.conf;

import com.chulung.website.filter.EscapeFilter;
import com.chulung.website.interceptor.AdminInterceptor;
import com.chulung.website.interceptor.GlobalTrackerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Autowired
    private GlobalTrackerInterceptor globalTrackerInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/","classpath:/static/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/siteroot/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Assert.notNull(adminInterceptor,"bean backendInterceptor canot found");
        Assert.notNull(globalTrackerInterceptor,"bean globalTrackerInterceptor canot found");
        registry.addInterceptor(globalTrackerInterceptor).addPathPatterns("/**");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/api/cms/**").excludePathPatterns("/api/cms/login");
        super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean addFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(escapeFilter());
        registration.addUrlPatterns("/comments/*");
        registration.setName("escapeFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public Filter escapeFilter() {
        return new EscapeFilter();
    }

}
