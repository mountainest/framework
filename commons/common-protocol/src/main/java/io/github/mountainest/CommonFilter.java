package io.github.mountainest;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class CommonFilter extends GenericFilter {
    private final AntPathMatcher MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (this.ignoreUrl(req.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String strUid = req.getHeader(CommonHeader.UID);
        String tenantId = req.getHeader(CommonHeader.TENANT_ID);
        if (StringUtils.isEmpty(strUid) || StringUtils.isEmpty(tenantId)) {
            this.unauthorized(servletResponse);
            return;
        }

        Long uid = Long.valueOf(strUid);
        String reqId = req.getHeader(CommonHeader.REQ_ID);
        SecurityContext.setHeader(uid, tenantId, reqId);
        filterChain.doFilter(servletRequest, servletResponse);
        SecurityContext.removeHeader();
    }

    private boolean ignoreUrl(String uri) {
//        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
//        GlobalParams globalParams = context.getBean(GlobalParams.class);
        String[] patterns = {"/*/doc.html", "/*/swagger-resources", "/*/v3/api-docs", "/*/webjars/**"};
        for (String pattern: patterns) {
            if (MATCHER.match(pattern, uri)) {
                return true;
            }
        }

        return false;
    }

    private void unauthorized(ServletResponse servletResponse) {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // response.getWriter().write("x-gw-reqid header is required.");
    }
}
