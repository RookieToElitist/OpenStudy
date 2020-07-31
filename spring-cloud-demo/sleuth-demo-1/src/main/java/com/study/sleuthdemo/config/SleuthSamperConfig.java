package com.study.sleuthdemo.config;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使得Sleuth不使用默认的
 */
@Configuration
public class SleuthSamperConfig {

    @Bean
    public AlwaysSampler alwaysSampler(){
        return new AlwaysSampler();
    }
}
