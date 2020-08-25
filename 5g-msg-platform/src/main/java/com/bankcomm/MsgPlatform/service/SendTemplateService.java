package com.bankcomm.MsgPlatform.service;

import com.bankcomm.MsgPlatform.entity.MessageTemplate;
import okhttp3.OkHttpClient;

public interface SendTemplateService {

    public void sendTemplate(MessageTemplate messageTemplate, OkHttpClient client);
}
