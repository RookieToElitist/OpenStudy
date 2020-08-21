package com.bankcomm.MsgPlatform.controller;

import com.bankcomm.MsgPlatform.entity.ConversationRequest;
import com.bankcomm.MsgPlatform.entity.ConversationResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ConversationRobot {

    Logger logger = LoggerFactory.getLogger(ConversationRobot.class);

    @PostMapping(value = "/conversation/info")
    public String info(@RequestBody ConversationRequest req, HttpServletRequest httpServletRequest){
        logger.info("请求体数据格式==>"+ httpServletRequest.getContentType());
        logger.info("请求头参数==>{appId="+  httpServletRequest.getHeader("appId")+",signature="+httpServletRequest.getHeader("Signature"));

        ConversationResponse res=new ConversationResponse();
        logger.info("请求体参数==>"+req.toString());
        Gson gson=new Gson();
        res.setSessionId(req.getSessionId());
        res.setMobileNo(req.getMobileNo());
        res.setServiceNo(req.getServiceNo());
        if("hello".equals(req.getMessage())){
            res.setSessionState("X");
            res.setMessage("Hello");
            return  gson.toJson(res);
        }else {
            res.setSessionState("A");
            res.setMessage("请问您需要了解什么?");
            return  gson.toJson(res);
        }
    }


}
