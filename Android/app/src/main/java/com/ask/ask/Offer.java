package com.ask.ask;

import java.io.Serializable;

/**
 * Created by alexander on 2/26/2018.
 *
 * Where offers are made.
 *
 */

public class Offer implements Serializable {

    private String requester_id;
    private String provider_id;
    private String offer_id;
    private String offer_price;
    private String itemFulfilling_id;
    private String itemProviding_id;
    private String begin_date;
    private String end_Date;
    private String description;
    private String message;

    private int NOT_SELECTED = -1;
    private int IN_PROGRESS = 0;
    private int SELECTED = 1;
    private int MATCHED = 2;
    private int status;

    public Offer(String requester_id, String provider_id, String offer_id, String offer_price, String itemFulfilling_id, String itemProviding_id, String begin_date, String end_Date, String description, String message) {
        this.requester_id = requester_id;
        this.provider_id = provider_id;
        this.offer_id = offer_id;
        this.offer_price = offer_price;
        this.itemFulfilling_id = itemFulfilling_id;
        this.itemProviding_id = itemProviding_id;
        this.begin_date = begin_date;
        this.end_Date = end_Date;
        this.description = description;
        this.message = message;
        this.status = IN_PROGRESS;
    }

    public String getRequester_id() {
        return requester_id;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public String getItemFulfilling_id() {
        return itemFulfilling_id;
    }

    public String getItemProviding_id() {
        return itemProviding_id;
    }

    public String getBeginDate() {
        return begin_date;
    }

    public String getEndDate() {
        return end_Date;
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

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "requester_id='" + requester_id + '\'' +
                ", provider_id='" + provider_id + '\'' +
                ", offer_id='" + offer_id + '\'' +
                ", offer_price='" + offer_price + '\'' +
                ", itemFulfilling_id='" + itemFulfilling_id + '\'' +
                ", itemProviding_id='" + itemProviding_id + '\'' +
                ", begin_date='" + begin_date + '\'' +
                ", end_Date='" + end_Date + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public String toDescriptiveString(){
        Item fulfillingItem = LocalData.getHashMapItemsById().get(this.getItemFulfilling_id());

        return "You are fulfilling a " + fulfillingItem.getName() +
                " from " + this.getBeginDate() + " to " + this.getEndDate() +
                " for $" + fulfillingItem.getPrice() + " per day.";
    }

}