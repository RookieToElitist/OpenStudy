package com.study.sleuthdemo2.config;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Samper的实现类，使得返回值永远是true，这样使得Sleuth不使用默认的抽样比例
 */
@Configuration
public class SleuthSamperConfig {

    @Bean
    public AlwaysSampler alwaysSampler(){
        return new AlwaysSampler();
    }
}
