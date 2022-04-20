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

public class CommonFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uid = req.getHeader(CommonHeader.UID);
        String tenantId = req.getHeader(CommonHeader.TENANTID);
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(tenantId)) {
            this.unauthorized(servletResponse);
            return;
        }

        SecurityContext.setHeader(uid, tenantId);
        filterChain.doFilter(servletRequest, servletResponse);
        SecurityContext.removeHeader();
    }

    private void unauthorized(ServletResponse servletResponse) {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // response.getWriter().write("x-gw-reqid header is required.");
    }
}
