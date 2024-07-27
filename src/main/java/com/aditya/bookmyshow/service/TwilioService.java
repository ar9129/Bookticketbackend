package com.aditya.bookmyshow.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("{twilio.account-sid}")
    private String accountSid ;

    @Value("{twilo.auth-token}")
    private String authToken ;

    @Value("{twilio.phone-number}")
    private String fromPhoneNumber ;

    public  void sendSms(String to, String messageBody){
        Twilio.init(accountSid, authToken);
        Message.creator(new PhoneNumber(to), new PhoneNumber(fromPhoneNumber), messageBody).create() ;
    }


}
