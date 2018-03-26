package com.ask.ask;

/**
 * Created by alexander on 2/26/2018.
 *
 * Used to hold the users and their items for each successful match.
 */

public class Match {

    private User requester;
    private User provider;
    private Item itemRequested;
    private Item itemProvided;

    public Match(User requester, User provider, Item itemRequested, Item itemProvided) {
        this.requester = requester;
        this.provider = provider;
        this.itemRequested = itemRequested;
        this.itemProvided = itemProvided;
    }



}