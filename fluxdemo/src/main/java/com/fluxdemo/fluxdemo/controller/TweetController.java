package com.fluxdemo.fluxdemo.controller;

import com.fluxdemo.fluxdemo.model.Tweet;
import com.fluxdemo.fluxdemo.repository.TweetRepository;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author bockey
 */
@RestController
public class TweetController {

    private TweetRepository tweetRepository;

    public Flux<Tweet> getAllTweets() {
        return null;
    }


}
