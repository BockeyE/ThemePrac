package com.fluxdemo.fluxdemo.controller;

import com.fluxdemo.fluxdemo.exceptions.TweetNotFoundException;
import com.fluxdemo.fluxdemo.model.Tweet;
import com.fluxdemo.fluxdemo.payload.ErrorResponse;
import com.fluxdemo.fluxdemo.repository.TweetRepository;
import com.sun.deploy.nativesandbox.comm.Response;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @DeleteMapping("/tweets/{id}")
    public Mono<ResponseEntity<Void>> deleteTweet(
            @PathVariable(value = "id") String id
    ) {
        return tweetRepository.findById(id)
                .flatMap(existingTweet -> tweetRepository.delete(existingTweet)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tweets are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tweet> streamAllTweets() {
        return tweetRepository.findAll();
    }




    /*
        Exception Handling Examples (These can be put into a @ControllerAdvice to handle exceptions globally)
    */

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Tweet with the same text already exists"));
    }

    @ExceptionHandler(TweetNotFoundException.class)
    public ResponseEntity handleTweetNotFoundException(TweetNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
