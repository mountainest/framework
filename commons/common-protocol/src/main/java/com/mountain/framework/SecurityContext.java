package com.mountain.framework;

import java.util.Optional;

public class SecurityContext {
    private static ThreadLocal<HeaderInfo> headers = new ThreadLocal<>();

    public static Optional<String> getUid() {
        HeaderInfo info = headers.get();
        if (info == null) {
            return Optional.empty();
        }

        return Optional.of(info.uid);
    }

    public static Optional<String> getTenantId() {
        HeaderInfo info = headers.get();
        if (info == null) {
            return Optional.empty();
        }

        return Optional.of(info.tenantId);
    }

    public static void setHeader(String uid, String tenantId) {
        HeaderInfo header = new HeaderInfo();
        header.uid = uid;
        header.tenantId = tenantId;

        headers.set(header);
    }

    public static void removeHeader() {
        headers.remove();
    }

    private static class HeaderInfo {
        private String uid;
        private String tenantId;
    }
}
