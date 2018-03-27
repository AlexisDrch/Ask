package com.ask.ask;

import java.util.HashMap;

/**
 * Created by alexisdurocher on 26/03/2018.
 * Singleton accessor from anywhere in the app.
 */

public abstract class LocalData {

    protected static User UserRequester = null;
    protected static User UserProvider = null;
    protected static HashMap<String, Item> itemHashMapById = null;
    protected static HashMap<String, Item> itemHashMapByName = null;

    public static HashMap<String, Item>  getHashMapItemsById(){

        if (itemHashMapById == null) {
            Item item_data[] = new Item[]
                    {
                            new Item(1, "Golf Club", null,
                                    5.00, null, R.mipmap.item_golfclub),
                            new Item(2, "Pot", null,
                                    10.00, null, R.mipmap.item_pot),
                            new Item(3, "Sleeping Bag", null,
                                    7.00, null, R.mipmap.item_sleepingbag),
                            new Item(4, "Surfboard", null,
                                    12.00, null, R.mipmap.item_surfboard),
                            new Item(5, "Tent", null,
                                    5.00, null, R.mipmap.item_tent)
                    };
            itemHashMapById = new HashMap<String, Item>();

            for (Item e : item_data) {
                itemHashMapById.put(""+e.getItem_id(), e);
            }
        }
        return itemHashMapById;
    }

    public static HashMap<String, Item>  getHashMapItemsByName(){

        if (itemHashMapByName == null) {
            Item item_data[] = new Item[]
                    {
                            new Item(1, "Golf Club", null,
                                    5.00, null, R.mipmap.item_golfclub),
                            new Item(2, "Pot", null,
                                    10.00, null, R.mipmap.item_pot),
                            new Item(3, "Sleeping Bag", null,
                                    7.00, null, R.mipmap.item_sleepingbag),
                            new Item(4, "Surfboard", null,
                                    12.00, null, R.mipmap.item_surfboard),
                            new Item(5, "Tent", null,
                                    5.00, null, R.mipmap.item_tent)
                    };
            itemHashMapByName = new HashMap<String, Item>();

            for (Item e : item_data) {
                itemHashMapByName.put(""+e.getName(), e);
            }
        }
        return itemHashMapByName;
    }

    // return unique instance of requester
    public static User geUserRequesterInstance() {
        if (UserRequester == null) {
            UserRequester = new User(9, "john123", "password",
                    "john@gmail.com", "John", "Smith",
                    "I'm confident in my password strength",
                    "https://en.wikipedia.org/wiki/Bob_the_Builder#/media/File:Bob_the_builder.jpg",
                    "770-293-3621", 31, 1, "191-103 Integer Rd. " +
                    "Corona, New Mexico 08219",
                    R.drawable.john_profile);
        }

        return UserRequester;
    }



}
