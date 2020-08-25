package com.bankcomm.MsgPlatform.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class MessageTemplate implements Serializable{
    private String sourceInstanceId;
    private String serviceNo;
    private String templateCode;
    private String downgrade = "NONE";
    private JSONObject downgradeData;
    private String senders;
    private JSONArray sendData;
    private JSONObject buttonDataList;

    public MessageTemplate() {
    }

    public String getSourceInstanceId() {
        return this.sourceInstanceId;
    }

    public void setSourceInstanceId(String sourceInstanceId) {
        this.sourceInstanceId = sourceInstanceId;
    }

    public String getServiceNo() {
        return this.serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public String getTemplateCode() {
        return this.templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getDowngrade() {
        return this.downgrade;
    }

    public void setDowngrade(String downgrade) {
        this.downgrade = downgrade;
    }

    public JSONObject getDowngradeData() {
        return this.downgradeData;
    }

    public void setDowngradeData(JSONObject downgradeData) {
        this.downgradeData = downgradeData;
    }

    public String getSenders() {
        return this.senders;
    }

    public void setSenders(String senders) {
        this.senders = senders;
    }

    public JSONArray getSendData() {
        return this.sendData;
    }

    public void setSendData(JSONArray sendData) {
        this.sendData = sendData;
    }

    public JSONObject getButtonDataList() {
        return this.buttonDataList;
    }

    public void setButtonDataList(JSONObject buttonDataList) {
        this.buttonDataList = buttonDataList;
    }
}
