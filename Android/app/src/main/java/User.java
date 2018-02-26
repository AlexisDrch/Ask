import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by alexander on 2/26/2018.
 *
 * User class to describe the profile of each person and their actions.
 *
 */

public class User {

    private String uuid;
    private String name;
    private int age;
    private URL profileImage;
    private String phoneNumber;
    private String address;
    private int rating;

    private ArrayList<Request> requests;
    private ArrayList<Offer> offers;
    private ArrayList<Match> matches;

    private LinkedList<Review> reviews;

    public User(String uuid, String name, int age, URL profileImage, String phoneNumber, String address) {
        this.uuid = uuid;
        this.name = name;
        this.age = age;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
        this.address = address;

        this.requests = new ArrayList<>();
        this.offers = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public String getUuid() {
        return uuid;
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

    public URL getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(URL profileImage) {
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
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
    
}