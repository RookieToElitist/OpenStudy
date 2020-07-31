package com.study.sleuthdemo2.config;

import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Span;

/**
 * 自定义一个抽样策略：只含有tag
 */
public class MySampler implements Sampler {

    private String tag;

    public MySampler(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean isSampled(Span span) {
        return span.tags().get(tag)!=null;
    }
}
