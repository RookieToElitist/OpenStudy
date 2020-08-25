package com.bankcomm.MsgPlatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Configuration
public class GlobalConfig {

    public static  HashMap<String,String> MSG_TEMPLATE_MAP=new HashMap<String,String>();

//    @Autowired
//    private Environment environment;

    @PostConstruct
    public  void init(){
        msgTemplateParams();
    }

    private void msgTemplateParams(){
        MSG_TEMPLATE_MAP.put("活期富","433543969448087552");
        MSG_TEMPLATE_MAP.put("惠民贷","446165381867732992");
    }

}
