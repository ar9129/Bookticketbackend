package com.aditya.bookmyshow.controller;


import com.aditya.bookmyshow.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1/sms")
public class SmsController {

    @Autowired
    private TwilioService twilioService ;

    @Autowired
    private CacheManager cacheManager ;

    @PostMapping("/send-code")
    public void sendCode(@RequestParam String phoneNumber){
        String verificationCode = generateVerificationCode() ;
        Cache cache =  cacheManager.getCache("verificationcodes");
        cache.put(phoneNumber, new verificationCode(verificationCode, Instant.now()));
        twilioService.sendSms(phoneNumber, "user Verification Code is" + verificationCode);
    }

    @PostMapping("/verify-code")
    public boolean verifyCode(@RequestParam String phoneNumber, @RequestParam String code){

        Cache cache =  cacheManager.getCache("verificationcodes");
        if(cache!=null){
            verificationCode storedCode = cache.get(phoneNumber, verificationCode.class) ;
            if(storedCode!=null && storedCode.getCode().equals(code)){
                if (Instant.now().isBefore(storedCode.getDate().plus(5, ChronoUnit.MINUTES))) {
                    return true; // Code is valid and not expired
                }
                cache.evict(phoneNumber); // Remove expired code
            }
            }
        return false;
    }




    private  String generateVerificationCode(){
        return Integer.valueOf(ThreadLocalRandom.current().nextInt(1000000, 10000000)).toString();
    }

    private static class verificationCode{
        private final String code ;
        private final Instant date ;

        private verificationCode(String code, Instant date) {
            this.code = code;
            this.date = date;
        }

        public String getCode() {
            return code;
        }

        public Instant getDate() {
            return date;
        }
    }
}
