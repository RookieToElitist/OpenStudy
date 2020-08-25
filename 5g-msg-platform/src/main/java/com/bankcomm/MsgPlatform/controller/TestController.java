package com.bankcomm.MsgPlatform.controller;

import com.alibaba.fastjson.JSONObject;
//import com.ztesoft.zsmart.bss.a2p.base.util.CommonUtils;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.LinkDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.MessageBodyDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.MessageInstancedDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.OperationTemplateDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.QueryTemplateDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.ResourceDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.SendMessageBodyDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.SendMessageResultDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.TemplateCreateDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.TemplateModifyDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.TemplatePageDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.TemplateVariableDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.dto.UploadResultDTO;
import com.ztesoft.zsmart.bss.a2p.sdk.enums.CardHeightEnum;
import com.ztesoft.zsmart.bss.a2p.sdk.enums.MessageTypeEnum;
import com.ztesoft.zsmart.bss.a2p.sdk.enums.TemplateFormatEnum;
import com.ztesoft.zsmart.bss.a2p.sdk.tools.A2pMessageService;
import com.ztesoft.zsmart.bss.a2p.sdk.tools.A2pTemplateService;
import com.ztesoft.zsmart.bss.a2p.sdk.tools.DefaultA2PMessagePuller;
//import com.ztesoft.zsmart.bss.a2p.start.MessageListenerImpl;
//import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * sdk消息发送demo<br>
 *
 * @author PengWei57<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2020/06/12 13:50 <br>
 * //@see com.ztesoft.zsmart.bss.a2p.start <br>
 */
@RestController
@RequestMapping("/")
//@Slf4j
public class TestController {
    /**
     * 监听上行消息、状态报告、消息事件Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:04<br>
     **/
//    @GetMapping("sdk/test")
//    public void test(){
//        String endpoints = "https://api.5gomsg.com";
//        DefaultA2PMessagePuller defaultA2PMessagePuller = new DefaultA2PMessagePuller(endpoints);
//
//        String arriveQueueName = "1_1065051121149_messageArriveQueen";
//        String upQueueName = "1_1065051121149_upMessageQueen";
//        String eventQueueName = "1_1065051121149_eventResponseQueen";
//
//        defaultA2PMessagePuller.startReceiveMsg("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8",
//                MessageTypeEnum.UP,upQueueName, new MessageListenerImpl());
//    }

//    @Autowired
//    RedisAtomicLong redisAtomicLong;
//
//    @GetMapping("/sdk/testId")
//    public long testId() {
//        long sequence = 0L;
//        try {
//            if (redisAtomicLong.get() == 0) {
//                redisAtomicLong.getAndSet(0L);
//            }
//            sequence = redisAtomicLong.incrementAndGet();
//        } catch (Exception ex) {
//            log.error("Failed to get sequence.", ex);
//        }
//        return sequence;
////        return CommonUtils.getId();
//    }

    /**
     * 查询模板列表列表Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @GetMapping("sdk/getTemplateList")
    @ResponseBody
    public String getTemplateList() {
        String endPoints = "https://api.5gomsg.com";
        A2pTemplateService a2pTemplateService = new A2pTemplateService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);
        QueryTemplateDTO message  = a2pTemplateService.queryTemplateList("", 1, 10);
        return JSONObject.toJSONString(message);
    }


    /**
     * 查询模板参数信息Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @GetMapping("sdk/queryTemplateVariables")
    @ResponseBody
    public String queryTemplateVariables() {
        String endPoints = "https://api.5gomsg.com";
        A2pTemplateService a2pTemplateService = new A2pTemplateService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);
        TemplateVariableDTO message  = a2pTemplateService.queryTemplateVariables("448662262841704448-TPL-1596081338491");
        return JSONObject.toJSONString(message);
    }

    /**
     * 查询模板发送消息体Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @GetMapping("sdk/queryMessageBodyByTemplateCode")
    @ResponseBody
    public String queryMessageBodyByTemplateCode() {
        String endPoints = "https://api.5gomsg.com";
        A2pTemplateService a2pTemplateService = new A2pTemplateService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);
        MessageBodyDTO message  = a2pTemplateService.queryMessageBodyByTemplateCode("448662262841704448-TPL-1596081338491");
        return JSONObject.toJSONString(message);
    }

    /**
     * 文件上传Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @PostMapping("/sdk/upload")
    @ResponseBody
    public String upload(@RequestParam MultipartFile mFile) throws IOException {
        String endPoints = "https://api.5gomsg.com";
        String fileName = mFile.getName();
        A2pMessageService a2pTemplateService = new A2pMessageService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);
        File file = new File(mFile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(mFile.getInputStream(), file);
        UploadResultDTO message  = a2pTemplateService.uploadFile(fileName, file);
        return JSONObject.toJSONString(message);
    }

    /**
     * 发送文本消息Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @PostMapping("/sdk/sendText")
    @ResponseBody
    public String sendText() {
        String endPoints = "https://api.5gomsg.com";
        A2pMessageService a2pTemplateService = new A2pMessageService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);

        SendMessageResultDTO message  = a2pTemplateService.sendText("xxxxxx", "15268583225");
        return JSONObject.toJSONString(message);
    }

    /**
     * 发送文件消息Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @PostMapping("/sdk/sendFile")
    @ResponseBody
    public String sendFile() {
        String endPoints = "https://api.5gomsg.com";
        A2pMessageService a2pTemplateService = new A2pMessageService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);

        SendMessageResultDTO message  = a2pTemplateService.sendFile("449109430844944384", "15268583225");
        return JSONObject.toJSONString(message);
    }
    /**
     * 发送模板消息Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @PostMapping("/sdk/sendTemplate")
    @ResponseBody
    public String sendTemplate() {
        String endPoints = "https://api.5gomsg.com";
        A2pMessageService a2pTemplateService = new A2pMessageService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);

        SendMessageBodyDTO sendMessageBodyDTO = new SendMessageBodyDTO();
        sendMessageBodyDTO.setSenders("15268583225");
        sendMessageBodyDTO.setTemplateCode("26001b540de44aedb2e16da6b68a6646");

        SendMessageResultDTO message  = a2pTemplateService.sendTemplateMessage(sendMessageBodyDTO);
        return JSONObject.toJSONString(message);
    }

    /**
     * 根据sourceInstanceId获取消息实例Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @PostMapping("/sdk/getMessageInstanceBySourceInstanceId")
    @ResponseBody
    public String getMessageInstanceBySourceInstanceId() {
        String endPoints = "https://api.5gomsg.com";
        A2pMessageService a2pTemplateService = new A2pMessageService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);

        MessageInstancedDTO message  = a2pTemplateService.getMessageInstanceBySourceInstanceId("450092710560616448");
        return JSONObject.toJSONString(message);
    }

    /**
     * 根据消息实例id获取实例信息Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @PostMapping("/sdk/getMessageInstanceByMessageInstanceId")
    @ResponseBody
    public String getMessageInstanceByMessageInstanceId() {
        String endPoints = "https://api.5gomsg.com";
        A2pMessageService a2pTemplateService = new A2pMessageService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);

        MessageInstancedDTO message  = a2pTemplateService.getMessageInstanceByMessageInstanceId("450092710560616448");
        return JSONObject.toJSONString(message);
    }

    /**
     * 创建消息模板Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @PostMapping("/sdk/createTemplate")
    @ResponseBody
    public String createTemplate(@RequestParam MultipartFile mFile, @RequestParam MultipartFile thumbFile) throws IOException {
        String endPoints = "https://api.5gomsg.com";
        A2pTemplateService a2pTemplateService = new A2pTemplateService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);

        TemplateCreateDTO templateCreateDTO = new TemplateCreateDTO();
        templateCreateDTO.setFormat(TemplateFormatEnum.marketing);
        templateCreateDTO.setTags("就餐");
        templateCreateDTO.setTemplateName("测试sdk创建");

        TemplatePageDTO templatePageDTO = new TemplatePageDTO();
        List<TemplatePageDTO> pageList = new ArrayList<>();
        pageList.add(templatePageDTO);
        templatePageDTO.setCardHeight(CardHeightEnum.MEDIUM_HEIGHT);
        templatePageDTO.setDescription("测试描述");
        templatePageDTO.setTitle("测试标题");

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setResourceType("picture");
        File file = new File(mFile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(mFile.getInputStream(), file);
        resourceDTO.setResourceName(mFile.getName());
        resourceDTO.setResourceData(file);
        File thumb = new File(thumbFile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(thumbFile.getInputStream(), thumb);
        resourceDTO.setThumbData(thumb);
        templatePageDTO.setResource(resourceDTO);
        templateCreateDTO.setPage(pageList);

        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setLinkName("测试");
        linkDTO.setLinkURL("https://www.baidu.com");
        List<LinkDTO> linkDTOList = new ArrayList<>();
        linkDTOList.add(linkDTO);
        templatePageDTO.setLinks(linkDTOList);

        OperationTemplateDTO message  = a2pTemplateService.createTemplate(templateCreateDTO);
        return JSONObject.toJSONString(message);
    }

    /**
     * 修改消息模板Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @PostMapping("/sdk/modifyTemplate")
    @ResponseBody
    public String modifyTemplate(@RequestParam MultipartFile mFile, @RequestParam MultipartFile thumbFile, @RequestParam String templateCode) throws IOException {
        String endPoints = "https://api.5gomsg.com";
        A2pTemplateService a2pTemplateService = new A2pTemplateService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);

        TemplateModifyDTO templateCreateDTO = new TemplateModifyDTO();
        templateCreateDTO.setFormat(TemplateFormatEnum.marketing);
        templateCreateDTO.setTags("就餐");
        templateCreateDTO.setTemplateName("测试sdk创建");
        templateCreateDTO.setTemplateCode(templateCode);

        TemplatePageDTO templatePageDTO = new TemplatePageDTO();
        List<TemplatePageDTO> pageList = new ArrayList<>();
        pageList.add(templatePageDTO);
        templatePageDTO.setCardHeight(CardHeightEnum.MEDIUM_HEIGHT);
        templatePageDTO.setDescription("测试描述2");
        templatePageDTO.setTitle("测试标题2");

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setResourceType("picture");
        resourceDTO.setResourceName(mFile.getName());
        File file = new File(mFile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(mFile.getInputStream(), file);
        resourceDTO.setResourceData(file);
        File thumb = new File(thumbFile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(thumbFile.getInputStream(), thumb);
        resourceDTO.setThumbData(thumb);
        templatePageDTO.setResource(resourceDTO);
        templateCreateDTO.setPage(pageList);

        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setLinkName("测试2");
        linkDTO.setLinkURL("https://www.baidu.com");
        List<LinkDTO> linkDTOList = new ArrayList<>();
        linkDTOList.add(linkDTO);
        templatePageDTO.setLinks(linkDTOList);

        OperationTemplateDTO message  = a2pTemplateService.modifyTemplate(templateCreateDTO);
        return JSONObject.toJSONString(message);
    }

    /**
     * 删除消息模板Demo <br>
     *
     * @author PengWei57 <br>
     * @taskId <br>
     * @createDate 2020/8/24 14:05<br>
     **/
    @PostMapping("/sdk/deleteTemplate")
    @ResponseBody
    public String deleteTemplate(@RequestParam String templateCode) {
        String endPoints = "https://api.5gomsg.com";
        A2pTemplateService a2pTemplateService = new A2pTemplateService("2fb1720b3cd34d2aa3e3e0eb7b825b31", "fee92941ed664149997cd91649ed6ae8", endPoints);
        boolean flag = a2pTemplateService.deleteTemplate(templateCode);
        return flag ? "true" : "false";
    }
}
