package com.mountain.framework.config;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;

@Slf4j
public class AccessFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        String name = this.getFilterName();
        ServletContext context = this.getServletContext();
        Enumeration<String> attributes = context.getAttributeNames();
        log.debug("{}, {}, {}", name, context.getServerInfo(), attributes);
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        Locale locale = request.getLocale();
        log.debug("语言：{}, {}", locale.toLanguageTag(), locale.toString());

        // 使用Accept-Language仅仅为了示例，实际上，应该用认证token信息
        String token = request.getHeader("Accept-Language");
        if (Strings.isBlank(token)) {
            this.unauthorized(servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void unauthorized(ServletResponse servletResponse) {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
