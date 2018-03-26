package com.ask.ask;

import java.util.LinkedList;


/**
 * Created by alexander on 2/26/2018.
 *
 * Links requests and offers together.
 *
 */

public class Request {
//
//    private int request_id;
//    private int item_id;
//    private int requester_id;
//    private String requester_name;
//    private String requester_surname;
//    private String requester_ppicture_url;
//    private int status;
//    private String begin_date;
//    private String end_date;
//    private String lon;
//    private String lat;
//    private String description;
//
//    private User requester;
//    private Item itemRequesting;
//    private int DELETED = -1;
//    private int IN_PROGRESS = 0;
//    private int OFFERS_PENDING = 1;
//    private int MATCHED = 2;
//
//    private LinkedList<Offer> offers;
//    private User matcher;
//
//    public Request(){}
//
//    public Request(User requester, Item itemRequesting, String begin_date, String end_date, String description) {
//        this.requester = requester;
//        requester_id = requester.getUser_id();
//
//        this.itemRequesting = itemRequesting;
//        item_id = itemRequesting.getItem_id();
//
//        this.begin_date = begin_date;
//        this.end_date = end_date;
//        this.description = description;
//
//        this.status = IN_PROGRESS;
//        this.offers = new LinkedList<>();
//        this.matcher = null;
//    }
//
//    public User getRequester() {
//        return requester;
//    }
//
//    public int getRequest_id() {
//        return request_id;
//    }
//
//    public Item getItem() {
//        return itemRequesting;
//    }
//
//    public String getBegin_date() {
//        return begin_date;
//    }
//
//    public String getEnd_date() {
//        return end_date;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public LinkedList<Offer> getOffers() {
//        return offers;
//    }
//
//    public User getMatcher() {
//        return matcher;
//    }
//
//    public boolean addOfferToRequest(Offer newOffer) {
//        return offers.add(newOffer);
//    }
//
//    public boolean confirmMatch(Offer offerConfirmed) {
//
//
//        return false;
//    }
//
//    @Override
//    public String toString() {
//        return "com.ask.ask.Request{" +
//                "requester=" + requester +
//                ", item=" + itemRequesting +
//                ", begin_date='" + begin_date + '\'' +
//                ", end_date='" + end_date + '\'' +
//                ", status='" + status + '\'' +
//                '}';
//    }

    private String lon;

    private String end_date;

    private String requester_ppicture_url;

    private String request_id;

    private String status;

    private String description;

    private String item_id;

    private String begin_date;

    private String requester_name;

    private String requester_surname;

    private String lat;

    private String requester_id;

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getEnd_date ()
    {
        return end_date;
    }

    public void setEnd_date (String end_date)
    {
        this.end_date = end_date;
    }

    public String getRequester_ppicture_url ()
    {
        return requester_ppicture_url;
    }

    public void setRequester_ppicture_url (String requester_ppicture_url)
    {
        this.requester_ppicture_url = requester_ppicture_url;
    }

    public String getRequest_id ()
    {
        return request_id;
    }

    public void setRequest_id (String request_id)
    {
        this.request_id = request_id;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getItem_id ()
    {
        return item_id;
    }

    public void setItem_id (String item_id)
    {
        this.item_id = item_id;
    }

    public String getBegin_date ()
    {
        return begin_date;
    }

    public void setBegin_date (String begin_date)
    {
        this.begin_date = begin_date;
    }

    public String getRequester_name ()
    {
        return requester_name;
    }

    public void setRequester_name (String requester_name)
    {
        this.requester_name = requester_name;
    }

    public String getRequester_surname ()
    {
        return requester_surname;
    }

    public void setRequester_surname (String requester_surname)
    {
        this.requester_surname = requester_surname;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    public String getRequester_id ()
    {
        return requester_id;
    }

    public void setRequester_id (String requester_id)
    {
        this.requester_id = requester_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [lon = "+lon+", end_date = "+end_date+", requester_ppicture_url = "+requester_ppicture_url+", request_id = "+request_id+", status = "+status+", description = "+description+", item_id = "+item_id+", begin_date = "+begin_date+", requester_name = "+requester_name+", requester_surname = "+requester_surname+", lat = "+lat+", requester_id = "+requester_id+"]";
    }

}