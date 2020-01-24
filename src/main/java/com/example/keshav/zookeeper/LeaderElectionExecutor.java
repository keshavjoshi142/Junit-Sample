package com.example.keshav.zookeeper;




import com.sun.javafx.util.Utils;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

@Scope("singleton")
public class LeaderElectionExecutor {

    private ZookeeperClient zookClient;


    private static final String LEADER_NODE = "/testleader";

    public LeaderElectionExecutor() {
        try {
            String hostname = InetAddress.getLocalHost().getHostName();

            String nodes = "localhost:2181";

            zookClient = new ZookeeperClient(nodes, LEADER_NODE, hostname);
            zookClient.start();

        } catch (Exception ex) {
            System.exit(1);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {
                System.out.println("closssinggggg ==================");
                try {
                    zookClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public ZookeeperClient getZookClient() {
        return zookClient;
    }



}
