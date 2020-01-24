package com.example.keshav;

import com.example.keshav.zookeeper.LeaderElectionExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@SpringBootApplication
public class KeshavApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KeshavApplication.class, args);
	}

	@Autowired
	LeaderElectionExecutor leaderElectionExecutor;

	@Override
	public void run(String... args) throws Exception {


		System.out.println("==================" + leaderElectionExecutor.getZookClient().isLeader());
	}
}
