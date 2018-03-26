package com.ask.ask;

import java.net.URL;
import java.util.LinkedList;

/**
 * Created by alexander on 2/26/2018.
 *
 * Item class to hold information for each individual item.
 *
 */

public class Item {

    private String categoryUuid;
    private String name;
    private LinkedList<String> tags;
    private double price;
    private URL itemImage;
    public char[] title;
    private int item_id;

    //TODO: connect icon with database pictures
    //temporary
    private int icon;

    public Item(int item_id, String name, LinkedList<String> tags, double price, URL itemImage, int icon) {
        this.item_id = item_id;
        this.categoryUuid = categoryUuid;
        this.name = name;
        this.tags = tags;
        this.price = price;
        this.itemImage = itemImage;
        this.icon = icon;
    }

    public int getItem_id() {
        return item_id;
    }

    public String getCategoryUuid() {
        return categoryUuid;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

//    public User getUser() {
//        return user;
//    }

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
                "item_id='" + item_id + '\'' +
                ", categoryUuid='" + categoryUuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}