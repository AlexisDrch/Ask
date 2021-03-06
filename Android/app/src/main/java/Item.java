import java.net.URL;
import java.util.LinkedList;

/**
 * Created by alexander on 2/26/2018.
 *
 * Item class to hold information for each individual item.
 *
 */

public class Item {

    private String uuid;
    private String name;
    private LinkedList<String> tags;
    private double price;
    private URL itemImage;

    public Item(String uuid, String name, LinkedList<String> tags, double price, URL itemImage) {
        this.uuid = uuid;
        this.name = name;
        this.tags = tags;
        this.price = price;
        this.itemImage = itemImage;
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

    public LinkedList<String> getTags() {
        return this.tags;
    }

    public boolean addTag(String newTag) {
        return this.tags.add(newTag);
    }

    public boolean removeTag(String tag) {
        return this.tags.remove(tag);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public URL getItemImage() {
        return itemImage;
    }

    public void setItemImage(URL itemImage) {
        this.itemImage = itemImage;
    }

    @Override
    public String toString() {
        return "Item{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}