package com.example.keshav.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.leader.Participant;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Closeable;
import java.io.IOException;

public class ZookeeperClient {

    private CuratorFramework client;
    private String latchPath;
    private String id;

    private LeaderLatch leaderLatch;

    public ZookeeperClient(String connString, String latchPath, String id) {
        client = CuratorFrameworkFactory.newClient(connString, new ExponentialBackoffRetry(1000, Integer.MAX_VALUE));
        this.id = id;
        this.latchPath = latchPath;
    }

    public void start() throws Exception {
        client.start();
        leaderLatch = new LeaderLatch(client, latchPath, id);
        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                System.out.println("<==========I am the leader============>");
            }

            @Override
            public void notLeader() {
                System.out.println("<============I am just a follower==============>");
            }
        });
        leaderLatch.start();
    }

    public boolean isLeader() {
        return leaderLatch.hasLeadership();
    }

    public Participant currentLeader() throws Exception {
        return leaderLatch.getLeader();
    }

    public void close() throws IOException {
        leaderLatch.close();
        client.close();
    }

    public CuratorFramework getClient() {
        return client;
    }

    public String getLatchPath() {
        return latchPath;
    }

    public String getId() {
        return id;
    }

    public LeaderLatch getLeaderLatch() {
        return leaderLatch;
    }

}