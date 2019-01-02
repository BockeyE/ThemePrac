package com.fluxdemo.fluxdemo.repository;

import com.fluxdemo.fluxdemo.model.Tweet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bockey
 */
@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {
}
