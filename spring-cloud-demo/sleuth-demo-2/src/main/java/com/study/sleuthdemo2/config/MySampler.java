package com.study.sleuthdemo2;

import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Span;

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
