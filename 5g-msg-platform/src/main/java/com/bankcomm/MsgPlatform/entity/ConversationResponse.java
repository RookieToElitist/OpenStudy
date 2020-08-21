package com.bankcomm.MsgPlatform.entity;


public class ConversationResponse {
    //会话Id（会话机器人返回的会话Id, 会以mobileNo为主键创建会话、A2P平台默认会扣留30分钟、过期清除）
    private String sessionId;
    //交互手机号码
    private String mobileNo;
    //Chatbot id
    private String serviceNo;
    //会话状态（A：正常交互状态， X：关闭状态）
    private String sessionState;
    //消息内容
    private String message;

    public ConversationResponse() {
    }

    public ConversationResponse(String sessionId, String mobileNo, String serviceNo, String sessionState, String message) {
        this.sessionId = sessionId;
        this.mobileNo = mobileNo;
        this.serviceNo = serviceNo;
        this.sessionState = sessionState;
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public String getSessionState() {
        return sessionState;
    }

    public void setSessionState(String sessionState) {
        this.sessionState = sessionState;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ConversationResponse{" +
                "sessionId='" + sessionId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", serviceNo='" + serviceNo + '\'' +
                ", sessionState='" + sessionState + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
