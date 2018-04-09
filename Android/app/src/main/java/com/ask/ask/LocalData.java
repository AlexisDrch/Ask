package com.ask.ask;

import java.util.HashMap;

/**
 * Created by alexisdurocher on 26/03/2018.
 * Singleton accessor from anywhere in the app.
 */

public abstract class LocalData {

    protected static User userRequester = null;
    protected static User userProvider = null;
    protected static User currentUser = null;
    protected static HashMap<String, Item> itemHashMapById = null;
    protected static HashMap<String, Item> itemHashMapByName = null;
    public static Boolean currentUserIsLoggedin = false;

    public static int REQUEST_WITH_PENDING_OFFERS = 0;
    public static int REQUEST_WITH_OFFER_SELECTED = 1;
    public static int OFFER_PENDING_FOR_REQUEST = 0;
    public static int OFFER_ACCEPTED_FOR_REQUEST = 1;
    public static int OFFER_DENIED = 2;

    public static HashMap<String, Item>  getHashMapItemsById(){

        if (itemHashMapById == null) {
            Item item_data[] = new Item[]
                    {
                            new Item(1, "Golf Club", null,
                                    5.00, null, R.drawable.item_golfclub),
                            new Item(2, "Pot", null,
                                    10.00, null, R.drawable.item_pot),
                            new Item(3, "Sleeping Bag", null,
                                    7.00, null, R.drawable.item_sleepingbag),
                            new Item(4, "Surfboard", null,
                                    12.00, null, R.drawable.item_surfboard),
                            new Item(5, "Tent", null,
                                    5.00, null, R.drawable.item_tent)
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
                                    10.00, null, R.drawable.item_golfclub),
                            new Item(2, "Pot", null,
                                    5.00, null, R.drawable.item_pot),
                            new Item(3, "Sleeping Bag", null,
                                    7.00, null, R.drawable.item_sleepingbag),
                            new Item(4, "Surfboard", null,
                                    12.00, null, R.drawable.item_surfboard),
                            new Item(5, "Tent", null,
                                    15.00, null, R.drawable.item_tent)
                    };
            itemHashMapByName = new HashMap<String, Item>();
            for (Item e : item_data) {
                itemHashMapByName.put(""+e.getName(), e);
            }
        }
        return itemHashMapByName;
    }

    public static void logInCurrentUser(User user){
        currentUserIsLoggedin = true;
        currentUser = user;
    }

    //TODO: need to update based on who logs in
    // return unique instance of current User
    public static User getCurrentUserInstance() {
        if (currentUser == null) {
            // that should not happen after the login
            currentUser = new User(1, "shi.carolyn", "askisd@best",
                    "cs947@cornell.edu", "Carolyn", "Shi",
                    "college student looking for outdoor equipment for her weekend adventures!",
                    "https://res.cloudinary.com/campus-job/image/upload/t_student-public-page/v1/profile_pictures/Qooxf0yZAH_20151129.jpeg",
                    "33767968976", 89, 0, "2 rue marconi",
                    R.drawable.nancy_profile);
        }
        return currentUser;
    }
    
}