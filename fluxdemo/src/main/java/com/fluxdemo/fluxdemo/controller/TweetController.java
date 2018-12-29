package com.fluxdemo.fluxdemo.controller;

import com.fluxdemo.fluxdemo.model.Tweet;
import com.fluxdemo.fluxdemo.repository.TweetRepository;
import com.sun.deploy.nativesandbox.comm.Response;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author bockey
 */
@RestController
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/tweets")
    public Flux<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @PostMapping("/tweets")
    public Mono<Tweet> createTweets(@Valid @RequestBody Tweet tweet) {
        return tweetRepository.save(tweet);
    }


    @GetMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweet>> getTweetById(@PathVariable(value = "id") String id) {
        return tweetRepository.findById(id)
                .map(savedTweet -> ResponseEntity.ok(savedTweet))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweet>> updateTweetById(@PathVariable(value = "id") String id
            , @Valid @RequestBody Tweet tweet
    ) {
        return tweetRepository.findById(id)
                .flatMap(existingTweet -> {
                    Tweet t = (Tweet) existingTweet;
                    t.setText(tweet.getText());
                    return tweetRepository.save(t);
                })
                .map(updateTweet -> new ResponseEntity<>(
                        updateTweet, HttpStatus.OK
                ))
                .defaultIfEmpty(
                        new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );
    }


}
