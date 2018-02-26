import java.util.LinkedList;

/**
 * Created by alexander on 2/26/2018.
 *
 * Links requests and offers together.
 *
 */

public class Request {

    private User requester;
    private Item itemRequesting;
    private String beginDate;
    private String endDate;
    private String description;

    private int DELETED = -1;
    private int IN_PROGRESS = 0;
    private int OFFERS_PENDING = 1;
    private int MATCHED = 2;
    private int status;

    private LinkedList<Offer> offers;
    private User matcher;

    public Request(User requester, Item itemRequesting, String beginDate, String endDate, String description) {
        this.requester = requester;
        this.itemRequesting = itemRequesting;
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

    /**
     *
     * @param newOffer
     * @return true of successful add, false if unsuccessful add
     */
    public boolean addOffer(Offer newOffer) {
        return offers.add(newOffer);
    }

    @Override
    public String toString() {
        return "Request{" +
                "requester=" + requester +
                ", item=" + itemRequesting +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}