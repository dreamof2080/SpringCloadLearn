package com.eureka.server.listener;

import com.eureka.server.mail.service.MailService;
import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 服务监听，上线、下线服务进行邮件通知
 */
@Component
public class EurekaStateListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MailService mailService;

    @Value("${mail.to}")
    private String[] sendTo;

    @Value("${spring.profiles.active}")
    private String active;

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event){
        if (active.equals("prod")) {
            mailService.sentSimpleMail("eureka服务下线提醒：" + event.getAppName(),"服务名：" + event.getAppName()+ "，服务地址："+event.getServerId(),sendTo);
        }
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event){
        InstanceInfo instanceInfo = event.getInstanceInfo();
        if (active.equals("prod")) {
            mailService.sentSimpleMail("eureka服务注册提醒：" + instanceInfo.getAppName(),"服务名：" + instanceInfo.getAppName()+ "，服务地址："+instanceInfo.getIPAddr()+":"+instanceInfo.getPort(),sendTo);
        }
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event){
        logger.info("服务{}进行续约",event.getAppName()+" "+event.getServerId());
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event){
        logger.info("注册中心启动,{}",System.currentTimeMillis());
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event){
        logger.info("注册中心服务端启动,{}",System.currentTimeMillis());
    }
}
