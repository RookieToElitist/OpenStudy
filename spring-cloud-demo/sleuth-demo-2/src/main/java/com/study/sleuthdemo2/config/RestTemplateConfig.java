package com.study.sleuthdemo2.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {


    @LoadBalanced //使用负载均衡机制,该机制由Ribbon提供
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
