// https://leetcode.com/problems/design-twitter/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by htyleo on 7/7/17.
 */
public class DesignTwitter {


  private Map<Integer, Set<Integer>> follows = new HashMap<>();
  private Map<Integer, List<Post>> posts = new HashMap<>();
  private long counter = 0;

  /**
   * Initialize your data structure here.
   */
  public DesignTwitter() {

  }

  /**
   * Compose a new tweet.
   */
  public void postTweet(int userId, int tweetId) {
    posts.putIfAbsent(userId, new ArrayList<>());
    List<Post> ps = posts.get(userId);
    ps.add(new Post(userId, tweetId, ps.size(), counter++));
  }

  /**
   * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must
   * be posted by users who the user followed or by the user herself. Tweets must be ordered from
   * most recent to least recent.
   */
  public List<Integer> getNewsFeed(int userId) {
    List<Integer> res = new ArrayList<>();

    PriorityQueue<Post> pq = new PriorityQueue<>((p1, p2) -> p1.timestamp > p2.timestamp ? -1 : 1);
    Set<Integer> uids = follows.getOrDefault(userId, new HashSet<>());
    uids.add(userId);
    for (int uid : uids) {
      List<Post> ps = posts.getOrDefault(uid, new ArrayList<>());
      if (!ps.isEmpty()) {
        Post p = ps.get(ps.size() - 1);
        pq.add(new Post(uid, p.tweetId, p.index, p.timestamp));
      }
    }

    for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
      Post p = pq.remove();
      res.add(p.tweetId);
      if (p.index > 0) {
        pq.add(posts.get(p.userId).get(p.index - 1));
      }
    }

    return res;
  }

  /**
   * Follower follows a followee. If the operation is invalid, it should be a no-op.
   */
  public void follow(int followerId, int followeeId) {
    follows.putIfAbsent(followerId, new HashSet<>());
    follows.get(followerId).add(followeeId);
  }

  /**
   * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
   */
  public void unfollow(int followerId, int followeeId) {
    if (follows.containsKey(followerId)) {
      follows.get(followerId).remove(followeeId);
    }
  }

  private static class Post {

    public final int userId;
    public final int tweetId;
    public final int index;
    public final long timestamp;

    public Post(int userId, int tweetId, int index, long timestamp) {
      this.userId = userId;
      this.tweetId = tweetId;
      this.index = index;
      this.timestamp = timestamp;
    }
  }
}
