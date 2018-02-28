/**
 * Created by alexander on 2/26/2018.
 *
 * Used to hold individual review elements after transactions occur between requester and provider.
 *
 */

public class Review {

    private User userGivingReview;
    private User userReceivingReview;
    private double rating;
    private String message;

    public Review(User userGivingReview, User userReceivingReview, double rating, String message) {
        this.userGivingReview = userGivingReview;
        this.userReceivingReview = userReceivingReview;
        this.rating = rating;
        this.message = message;
    }

    public User getUserGivingReview() {
        return userGivingReview;
    }

    public User getUserReceivingReview() {
        return userReceivingReview;
    }

    public double getRating() {
        return rating;
    }

    public String getMessage() {
        return message;
    }

}