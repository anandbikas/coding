package com.anand.coding.design;

import java.util.*;

/**
 * Find 10 recent tweets of the user or all its followee.
 * Heap/PriorityQueue, HashTable, List.
 */
public class _01_TwitterNewsFeed {

    private static class Tweet{
        int id;
        long epoc;

        public Tweet(int id){
            this.id = id;
            this.epoc = System.nanoTime();
        }
    }

    private static class User{
        int id;
        Set<Integer> followers = new HashSet<>();
        List<Tweet> tweets = new ArrayList<>();

        public User(int id) {
            this.id = id;
        }
    }

    Map<Integer, User> users;

    public _01_TwitterNewsFeed() {
        users = new HashMap<>();
    }


    /**
     * Post tweet
     *
     * @param userId
     * @param tweetId
     */
    public void postTweet(int userId, int tweetId) {
        if(!users.containsKey(userId)){
            users.put(userId, new User(userId));
        }
        Tweet tweet = new Tweet(tweetId);
        users.get(userId).tweets.add(tweet);

    }

    /**
     * Retrieve 10 most recent tweets of the user or all its followee to display news feed.
     *
     * @param userId
     * @return
     */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq = new PriorityQueue<>((t1,t2) -> (int)(t2.epoc - t1.epoc));

        List<Integer> tweets = new ArrayList<>();
        if(users.containsKey(userId)){
            pq.addAll(users.get(userId).tweets);
            users.get(userId).followers.forEach(uid -> pq.addAll(users.get(uid).tweets));
        }

        for(int i=0; i<10 && !pq.isEmpty(); i++){
            tweets.add(pq.remove().id);
        }

        return tweets;
    }

    /**
     * Follower follows a followee.
     *
     * @param followerId
     * @param followeeId
     */
    public void follow(int followerId, int followeeId) {
        if(!users.containsKey(followerId)){
            users.put(followerId, new User(followerId));
        }
        if(!users.containsKey(followeeId)){
            users.put(followeeId, new User(followeeId));
        }
        users.get(followerId).followers.add(followeeId);
    }

    /**
     * Follower unfollows a followee.
     *
     * @param followerId
     * @param followeeId
     */
    public void unfollow(int followerId, int followeeId) {
        if(!users.containsKey(followerId)){
            users.put(followerId, new User(followerId));
        }
        if(!users.containsKey(followeeId)){
            users.put(followeeId, new User(followeeId));
        }
        users.get(followerId).followers.remove(followeeId);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {
        _01_TwitterNewsFeed twitter = new _01_TwitterNewsFeed();

        twitter.postTweet(1,5);
        System.out.println(twitter.getNewsFeed(1));

        twitter.follow(1,2);

        twitter.postTweet(2,6);
        System.out.println(twitter.getNewsFeed(1));

        twitter.unfollow(1,2);

        System.out.println(twitter.getNewsFeed(1));
    }
}
