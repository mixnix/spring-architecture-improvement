package com.example.demo.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class HazelcastConfig {

    @Bean
    public Config configHazelcast(){
        return new Config()
                .setInstanceName("instance-1")
                .addMapConfig(new MapConfig().setName("user").setEvictionConfig(new EvictionConfig()
                        .setEvictionPolicy(EvictionPolicy.LFU).setSize(24)).setTimeToLiveSeconds(360));
        //least frequently used i ta druga, w jaki sposob decyduje co usunac a co zachowac
        //w momencie gdy zostanie zapelniony cache
    }

}
