package com.wjy.bean;

/**
 * @Date 2018/10/27
 * @Author ybxxszl
 * @Desc 邮件基本信息
 **/
public class MailInfo {

    /**
     * 收件人地址
     */
    private String recipientAddress;

    /**
     * 邮件内容
     */
    private String text;

    public MailInfo() {
    }

    public MailInfo(String recipientAddress, String text) {
        this.recipientAddress = recipientAddress;
        this.text = text;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MailInfo{" +
                "recipientAddress='" + recipientAddress + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

}
