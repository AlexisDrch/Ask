/**
 * Created by alexander on 2/26/2018.
 *
 * Where offers are made.
 *
 */

public class Offer {

    private User provider;
    private Item itemFulfilling;
    private Item itemProviding;
    private String message;

    private int NOT_SELECTED = -1;
    private int IN_PROGRESS = 0;
    private int SELECTED = 1;
    private int MATCHED = 2;
    private int status;

    private User requester; //only if matched together, otherwise offer will be deleted

    public Offer(User provider, Item itemFulfilling, Item itemProviding, String message) {
        this.provider = provider;
        this.itemFulfilling = itemFulfilling;
        this.itemProviding = itemProviding;
        this.message = message;
        this.status = IN_PROGRESS;
        this.requester = null;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getRequester() {
        return requester;
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