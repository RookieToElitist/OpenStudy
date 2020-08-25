package com.bankcomm.MsgPlatform.controller;

import com.bankcomm.MsgPlatform.config.GlobalConfig;
import com.bankcomm.MsgPlatform.entity.ConversationRequest;
import com.bankcomm.MsgPlatform.entity.ConversationResponse;
import com.bankcomm.MsgPlatform.entity.MessageTemplate;
import com.bankcomm.MsgPlatform.service.SendTemplateService;
import com.google.gson.Gson;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.SendMessageBodyDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.SendMessageResultDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.tools.A2pMessageService;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ConversationRobotController {

    Logger logger = LoggerFactory.getLogger(ConversationRobotController.class);

//    @Autowired
//    SendTemplateService sendTemplateService;

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
        }else if("惠民贷".equals(req.getMessage())){
            String templateCode = GlobalConfig.MSG_TEMPLATE_MAP.get(req.getMessage());
            String endPoints = "https://api.5gomsg.com";
            A2pMessageService a2pTemplateService = new A2pMessageService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);

            SendMessageBodyDTO sendMessageBodyDTO = new SendMessageBodyDTO();
            sendMessageBodyDTO.setSenders(req.getMobileNo());
            sendMessageBodyDTO.setTemplateCode(templateCode);

            SendMessageResultDTO message  = a2pTemplateService.sendTemplateMessage(sendMessageBodyDTO);


            res.setSessionState("X");
            res.setMessage(null);
            return  gson.toJson(res);
        }else if("活期富".equals(req.getMessage())){
            String templateCode = GlobalConfig.MSG_TEMPLATE_MAP.get(req.getMessage());
            String endPoints = "https://api.5gomsg.com";
            A2pMessageService a2pTemplateService = new A2pMessageService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);

            SendMessageBodyDTO sendMessageBodyDTO = new SendMessageBodyDTO();
            sendMessageBodyDTO.setSenders(req.getMobileNo());
            sendMessageBodyDTO.setTemplateCode(templateCode);

            SendMessageResultDTO message  = a2pTemplateService.sendTemplateMessage(sendMessageBodyDTO);

            res.setSessionState("X");
            res.setMessage(null);
            return  gson.toJson(res);
        }else {
            res.setSessionState("A");
            res.setMessage("请问您需要了解什么?");
            return  gson.toJson(res);
        }
    }


}
