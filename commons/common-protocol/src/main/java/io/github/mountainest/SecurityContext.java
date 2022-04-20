package io.github.mountainest;

import java.util.Optional;

public class SecurityContext {
    private static ThreadLocal<HeaderInfo> headers = new ThreadLocal<>();

    public static Optional<Long> getUid() {
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

    public static Optional<String> getReqId() {
        HeaderInfo info = headers.get();
        if (info == null) {
            return Optional.empty();
        }

        return Optional.of(info.reqId);
    }

    public static void setHeader(Long uid, String tenantId, String reqId) {
        HeaderInfo header = new HeaderInfo();
        header.uid = uid;
        header.tenantId = tenantId;
        header.reqId = reqId;

        headers.set(header);
    }

    public static void removeHeader() {
        headers.remove();
    }

    private static class HeaderInfo {
        private Long uid;
        private String tenantId;
        private String reqId;
    }
}
