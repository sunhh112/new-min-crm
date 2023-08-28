package com.mincrm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by sunhh on 2020/3/28.
 */
@Component
public class AppConfig {

    @Value("${ddd.isSendSms}")
    public boolean isSendSms;



}
