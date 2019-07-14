package com.eureka.server.mail.service;

import java.util.Map;

/**
 * 邮件发送接口
 */
public interface MailService {

    /**
     * 发送简单邮件
     * @param subject 主题
     * @param text 邮件内容
     * @param users 接收用户
     */
    void sentSimpleMail(String subject,String text,String... users);

    /**
     * 发送HTML邮件
     * @param subject
     * @param html
     * @param files 文件路径
     * @param users
     */
    void sentHTMLMail(String subject,String html,Map<String,String> files,String... users);

    /**
     * 发送模板邮件
     * @param subject 主题
     * @param params 参数
     * @param templateName 模板名称
     * @param users 接收用户
     */
    void sentFreeMarkerTemplateMail(String subject, Map<String,Object> params, String templateName, String... users);


    void sentThymeleafTemplateMail(String subject, Map<String,Object> params, String templateName, String... users);
}
