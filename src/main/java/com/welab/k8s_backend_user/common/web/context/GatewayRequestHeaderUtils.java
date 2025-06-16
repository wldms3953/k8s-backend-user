package com.welab.k8s_backend_user.common.web.context;


import com.welab.k8s_backend_user.common.exception.NotFound;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class GatewayRequestHeaderUtils {
    public static String getRequestHeaderParamAsString(String key) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest().getHeader(key);
    }

    public static String getUserId() {
        return getRequestHeaderParamAsString("X-Auth-UserId");
    }

    public static String getClientDevice() {
        return getRequestHeaderParamAsString("X-Client-Device");
    }

    public static String getClientAddress() {
        return getRequestHeaderParamAsString("X-Client-Address");
    }

    public static String getUserIdOrThrowException() {
        String userId = getUserId();
        if (userId == null) {
            throw new NotFound("헤더에 userId 정보가 없습니다.");
        }

        return userId;
    }

    public static String getClientDeviceOrThrowException() {
        String clientDevice = getClientDevice();
        if (clientDevice == null) {
            throw new NotFound("헤더에 사용자 디바이스 정보가 없습니다.");
        }

        return clientDevice;
    }

    public static String getClientAddressOrThrowException() {
        String clientAddress = getClientAddress();
        if (clientAddress == null) {
            throw new NotFound("헤더에 사용자 IP 주소 정보가 없습니다.");
        }

        return clientAddress;
    }
}
