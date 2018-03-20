package com.ask.ask;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Created by alexander on 2/26/2018.
 *
 * com.ask.ask.User class to describe the profile of each person and their actions.
 *
 */

public class User {

    private int user_id;
    private String name;
    private int age;

    //changed from URL to int
    private int profileImage;
    private String phoneNumber;
    private String address;
    private int rating;

    private ArrayList<Request> requests;
    private ArrayList<Offer> offers;
    private ArrayList<Match> matches;

    private LinkedList<Review> reviews;

    public User(int user_id, String name, int age, int profileImage, String phoneNumber, String address) {
        this.user_id = user_id;
        this.name = name;
        this.age = age;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
        this.address = address;

        this.requests = new ArrayList<>();
        this.offers = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public int getUserId() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getProfileImage() {
        return profileImage;
    }

    //changed URL to int
    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LinkedList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    //actions

    public void makeRequest() {

    }

    public void makeOffer() {

    }

    @Override
    public String toString() {
        return "com.ask.ask.User{" +
                "uuid='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }

}