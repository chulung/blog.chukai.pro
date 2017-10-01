package com.wchukai.web.filter;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * @author wchukai
 */

public class EscapeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {

    }

    /**
     * 防xss注入
     *
     * @author wchukai
     */
    public static class XssRequestWrapper extends HttpServletRequestWrapper {

        public XssRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        public String getParameter(String name) {
            String value = super.getParameter(name);
            if (value == null) {
                return null;
            }
            return StringEscapeUtils.escapeHtml4(value);
        }

        public String[] getParameterValues(String name) {
            String[] values = super.getParameterValues(name);
            if (values == null) {
                return null;
            }
            String[] newValues = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                newValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
            }
            return newValues;
        }
    }
}
