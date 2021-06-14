package com.mycompany.myapp.sms.builder;

import com.github.qcloudsms.SmsMultiSender;
import com.mycompany.myapp.config.ApplicationProperties;
import com.mycompany.myapp.domain.SmsConfig;
import com.mycompany.myapp.sms.SmsTemplate;
import com.mycompany.myapp.sms.tencent.TencentSmsTemplate;
import javax.cache.CacheManager;

/**
 * 腾讯云短信构建类
 *
 */
public class TencentSmsBuilder {

    public static SmsTemplate template(SmsConfig sms, CacheManager cacheManager) {
        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.getSms().setTemplateId(sms.getTemplateId());
        applicationProperties.getSms().setAccessKey(sms.getAccessKey());
        applicationProperties.getSms().setSecretKey(sms.getSecretKey());
        applicationProperties.getSms().setSignName(sms.getSignName());
        SmsMultiSender smsSender = new SmsMultiSender(Integer.parseInt(applicationProperties.getSms().getAccessKey()), sms.getSecretKey());
        return new TencentSmsTemplate(applicationProperties, smsSender, cacheManager);
    }
}
