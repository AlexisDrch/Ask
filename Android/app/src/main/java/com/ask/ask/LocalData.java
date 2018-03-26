package com.ask.ask;

import java.util.HashMap;

/**
 * Created by alexisdurocher on 26/03/2018.
 */

public abstract class LocalData {


    public static HashMap<String, Item>  getHashMapItems(){
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

        //convert item_array into Hashmap

        HashMap<String, Item> itemHashMap = new HashMap<String, Item>();

        for (Item e : item_data) {
            itemHashMap.put(""+e.getItem_id(), e);
        }
        return itemHashMap;
    }
}
