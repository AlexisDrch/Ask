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
    private String user_name;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String description;
    private String ppicture_url;
    private String phone_num;
    private int age;
    private int sex;
    private String address;


//    private int country;
//    //changed from URL to int
//TODO: Should remove this and pull pictures from urls
    private int profileImage;
//    private String phoneNumber;
    private int rating;

    private ArrayList<Request> requests;
    private ArrayList<Offer> offers;
    private ArrayList<Match> matches;

    private LinkedList<Review> reviews;

    public User(int user_id, String user_name, String password, String email, String name,
                String surname, String description, String ppicture_url, String phone_num, int age,
                int sex, String address, int profileImage) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.ppicture_url = ppicture_url;
        this.phone_num = phone_num;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.profileImage = profileImage;
//        this.phoneNumber = phoneNumber;

        this.requests = new ArrayList<>();
        this.offers = new ArrayList<>();
        this.matches = new ArrayList<>();
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

    //    }

    public int getUser_id() {
        return user_id;
    }




    public String getUser_name() {
        return user_name;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPpicture_url() {
        return ppicture_url;
    }

    public void setPpicture_url(String ppicture_url) {
        this.ppicture_url = ppicture_url;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public int getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }


    //        return profileImage;
//    }
//    public void setProfileImage(int profileImage) {
//        this.profileImage = profileImage;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }


}