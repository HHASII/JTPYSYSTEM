package com.capgemini.jtp.entity;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Classname : MenuMeta
 * @author: Jason Jin
 * @date: 2019/5/19 11:48 PM
 */
public class MenuMeta implements Serializable {

    private boolean keepAlive;

    private boolean requireAuth;

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
