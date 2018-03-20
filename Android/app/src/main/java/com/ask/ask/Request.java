package com.ask.ask;

import java.util.LinkedList;
import com.google.gson.annotations.SerializedName;


/**
 * Created by alexander on 2/26/2018.
 *
 * Links requests and offers together.
 *
 */

public class Request {

    @SerializedName("request_id")
    private int request_id;

    @SerializedName("item_id")
    private int item_id;

    @SerializedName("requester_id")
    private int requester_id;

    @SerializedName("provider_id")
    private int provider_id;

    @SerializedName("begin_date")
    private String beginDate;

    @SerializedName("end_date")
    private String endDate;

    @SerializedName("description")
    private String description;

    @SerializedName("lon")
    private int lon;

    @SerializedName("lat")
    private int lat;


    private User requester;
    private Item itemRequesting;

    private int DELETED = -1;
    private int IN_PROGRESS = 0;
    private int OFFERS_PENDING = 1;
    private int MATCHED = 2;
    private int status;

    private LinkedList<Offer> offers;
    private User matcher;

    public Request(User requester, Item itemRequesting, String beginDate, String endDate, String description) {
        this.requester = requester;
        requester_id = requester.getUserId();

        this.itemRequesting = itemRequesting;
        item_id = itemRequesting.getItem_id();

        this.beginDate = beginDate;
        this.endDate = endDate;
        this.description = description;

        this.status = IN_PROGRESS;
        this.offers = new LinkedList<>();
        this.matcher = null;
    }

    public User getRequester() {
        return requester;
    }

    public int getRequest_id() {
        return request_id;
    }

    public Item getItem() {
        return itemRequesting;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LinkedList<Offer> getOffers() {
        return offers;
    }

    public User getMatcher() {
        return matcher;
    }

    public boolean addOfferToRequest(Offer newOffer) {
        return offers.add(newOffer);
    }

    public boolean confirmMatch(Offer offerConfirmed) {


        return false;
    }

    @Override
    public String toString() {
        return "com.ask.ask.Request{" +
                "requester=" + requester +
                ", item=" + itemRequesting +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}