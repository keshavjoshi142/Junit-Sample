package com.example.keshav;


import com.example.keshav.zookeeper.LeaderElectionExecutor;
import com.example.keshav.zookeeper.ZookeeperClient;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ZookeeperIntegrationTest {

    //private ApplicationContext applicationContext;
    @Test
    public void test() {
        LeaderElectionExecutor leaderElectionExecutor1 = new LeaderElectionExecutor();
        LeaderElectionExecutor leaderElectionExecutor2 = new LeaderElectionExecutor();
        //applicationContext.getBean(LeaderElectionExecutor.class);
    }


}
