package com.eureka.server.mail.service.impl;

import com.eureka.server.mail.service.MailService;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sentSimpleMail(String subject, String text, String... users) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(this.from);
        mailMessage.setTo(users);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }

    @Override
    public void sentHTMLMail(String subject, String html,Map<String,String> files, String... users) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 是否发送的邮件是富文本(附件、图片、html等)
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(this.from);
            messageHelper.setTo(users);
            messageHelper.setSubject(subject);
            messageHelper.setText(html,true);
            if (files != null) {
                files.entrySet().stream().forEach(entrySet -> {
                    File file = new File(entrySet.getValue());
                    if (file.exists()) {
                        try {
                            messageHelper.addAttachment(entrySet.getKey(),new FileSystemResource(file));
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sentTemplateMail(String subject, Map<String, Object> params, String templateName, String... users) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(this.from);
            messageHelper.setTo(users);

            Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            configuration.setClassForTemplateLoading(this.getClass(),"/templates");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(templateName),params);

            messageHelper.setSubject(subject);
            messageHelper.setText(html,true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
