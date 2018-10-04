/*
 * Copyright (c) 2018. AK
 */

package com.spyapp.server.core.beans.services;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Scope("singleton")
public class LoginService {

    private HashMap<String, Cookie> stringCookieHashMap;

    private static LoginService instance = null;

    public HashMap<String, Cookie> getStringCookieHashMap() {
        return stringCookieHashMap;
    }

    public void setStringCookieHashMap(HashMap<String, Cookie> stringCookieHashMap) {
        this.stringCookieHashMap = stringCookieHashMap;
    }

    private LoginService(HashMap<String, Cookie> stringCookieHashMap) {
        this.stringCookieHashMap = stringCookieHashMap;
    }

    private LoginService() {
        this.stringCookieHashMap = new HashMap<>();
    }

    public void save(String username, Cookie cookie) {
        stringCookieHashMap.put(username, cookie);
    }

    public boolean checkIfValid(String key) {

        AtomicBoolean valid = new AtomicBoolean(false);

        stringCookieHashMap.forEach((k, v) -> {

            if (v.getValue().equals(key) && v.getMaxAge() > 0) {
                valid.set(true);
            }
        });

        return valid.get();
    }


}
