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

    private void unauthorized(ServletResponse servletResponse) {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // response.getWriter().write("x-gw-reqid header is required.");
    }
}
