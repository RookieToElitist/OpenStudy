package com.bankcomm.MsgPlatform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bankcomm.MsgPlatform.entity.MessageTemplate;
import com.bankcomm.MsgPlatform.service.SendTemplateService;
import com.ztesoft.zsmart.bss.a2p.sdk.tools.TokenGetterForA2P;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class SendTemplateServiceImpl implements SendTemplateService {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static  String URL = "https://api.5gomsg.com";

    private TokenGetterForA2P tokenGetter;
    private String accessKeyId;
    private String accessKeySecret;

    public SendTemplateServiceImpl() {
    }

    public SendTemplateServiceImpl(String accessKeyId, String accessKeySecret, String URL) {
        this.URL = URL;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.init();
    }

    public SendTemplateServiceImpl(String accessKeyId, String accessKeySecret) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.init();
    }

    private void init() {
        this.tokenGetter = new TokenGetterForA2P(this.accessKeyId, this.accessKeySecret, this.URL);
    }


    /**
     * 发送post请求到聚合平台
     * @param messageTemplate
     * @param client
     */
    @Override
    public void sendTemplate(MessageTemplate messageTemplate, OkHttpClient client) {

        RequestBody body = RequestBody.create(JSON, JSONObject.toJSONString(messageTemplate));

        Request request = new Request.Builder()
                .url(URL+"/a2p/api/v1/message/sendMessage")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
