package com.ask.ask;

/**
 * Created by alexander on 2/26/2018.
 *
 * Where offers are made.
 *
 */

public class Offer {

    private User requester;
    private User provider;
    private Item itemFulfilling;
    private Item itemProviding;
    private String beginDate;
    private String endDate;
    private String description;
    private String message;

    private int NOT_SELECTED = -1;
    private int IN_PROGRESS = 0;
    private int SELECTED = 1;
    private int MATCHED = 2;
    private int status;

    public Offer(User requester, User provider, Item itemFulfilling, Item itemProviding, String beginDate, String endDate, String description, String message) {
        this.requester = requester;
        this.provider = provider;
        this.itemFulfilling = itemFulfilling;
        this.itemProviding = itemProviding;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.description = description;
        this.message = message;
        this.status = IN_PROGRESS;
        this.requester = null;
    }

    public User getRequester() {
        return requester;
    }

    public User getProvider() {
        return provider;
    }

    public Item getItemFulfilling() {
        return itemFulfilling;
    }

    public Item getItemProviding() {
        return itemProviding;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "provider=" + provider +
                ", itemFulfilling=" + itemFulfilling +
                ", itemProviding=" + itemProviding +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

}