package com.wjy.bean;

/**
 * @Date 2018/10/27
 * @Author ybxxszl
 * @Desc 消息队列基本信息
 **/
public class MQInfo {

    /**
     * 消息队列类型
     */
    private String type;

    /**
     * 消息队列名称
     */
    private String name;

    /**
     * 邮件基本信息
     */
    private MailInfo mailInfo;

    /**
     * 短信基本信息
     */
    private SMSInfo smsInfo;

    public MQInfo() {
    }

    public MQInfo(String type, String name, MailInfo mailInfo, SMSInfo smsInfo) {
        this.type = type;
        this.name = name;
        this.mailInfo = mailInfo;
        this.smsInfo = smsInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MailInfo getMailInfo() {
        return mailInfo;
    }

    public void setMailInfo(MailInfo mailInfo) {
        this.mailInfo = mailInfo;
    }

    public SMSInfo getSMSInfo() {
        return smsInfo;
    }

    public void setSMSInfo(SMSInfo smsInfo) {
        this.smsInfo = smsInfo;
    }

    @Override
    public String toString() {
        return "MQInfo{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", mailInfo=" + mailInfo +
                ", smsInfo=" + smsInfo +
                '}';
    }

}
