package com.passbook.helper;

import com.passbook.core.Device;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

public class Authenticator {

    private static final String HTTP_AUTHORIZATION = "HTTP_AUTHORIZATION";
    private static final String TOKEN = "ApplePass %s";

    public static boolean isAuthorized(HttpHeaders headers, Device device) {
        MultivaluedMap<String, String> headerParams = headers.getRequestHeaders();
        return String.format(TOKEN, device.getAuthenticationToken()).equals(headerParams.getFirst(HTTP_AUTHORIZATION));
    }

}
