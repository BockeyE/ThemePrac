package com.fluxdemo.fluxdemo.exceptions;

/**
 * @author bockey
 */
public class TweetNotFoundException extends RuntimeException {
    public TweetNotFoundException(String tweetId) {
        super("Tweet not found with id " + tweetId);
    }

}
