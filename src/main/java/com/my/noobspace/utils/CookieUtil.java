package com.my.noobspace.utils;

import jakarta.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie addCookie(String name, String value) {

        Cookie cookie = new Cookie("refresh_token", value);
        cookie.setPath("/");
        cookie.setDomain("34.64.246.19");
//        cookie.setDomain("localhost");
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);

        return cookie;
    }
}
