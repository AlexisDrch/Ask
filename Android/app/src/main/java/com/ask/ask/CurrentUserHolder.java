package com.ask.ask;

/**
 * Created by alexander on 3/26/2018.
 *
 * Singleton class to hold values of the user currently logged in for access through entire project.
 *
 * Use by: String userId = CurrenUserHolder.getInstance().getUserId();
 */

public class CurrentUserHolder {

    private String userId;
    private static final CurrentUserHolder currentUserHolder = new CurrentUserHolder();

    public static CurrentUserHolder getInstance() {
        return currentUserHolder;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

}