package com.example.hazelclient.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelcastClientConfig {

    @Bean
    @Qualifier("hazelcastClientConfig")
    public ClientConfig clientConfig()
    {

        ClientConfig clientConfig = new ClientConfig();

        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();

//        networkConfig
//        .setAddresses(Arrays.asList("http://localhost:5701"))
//        .setConnectionAttemptLimit(0)
//        .setConnectionAttemptPeriod(3000)
//        .setConnectionTimeout(60000)
//        .setRedoOperation(true);
//
//       
        
        networkConfig.addAddress("localhost:5701")
        .setSmartRouting(true)
        .addOutboundPortDefinition("34700-34710")
        .setRedoOperation(true)
        .setConnectionTimeout(5000)
        .setConnectionAttemptLimit(5);
        
        return clientConfig;
    }
    @Bean
    public  HazelcastInstance hazelcastInstance(ClientConfig clientConfig )  {
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

}
