package com.example.keshav.zookeeper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public LeaderElectionExecutor leaderElectionExecutor() {
        return LeaderElectionExecutor.getInstance();

    }
}
