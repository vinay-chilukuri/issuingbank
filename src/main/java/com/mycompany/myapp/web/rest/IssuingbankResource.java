package com.mycompany.myapp.web.rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * IssuingbankResource controller
 */
@RestController
@RequestMapping("/api/issuingbank")
public class IssuingbankResource {

    private final Logger log = LoggerFactory.getLogger(IssuingbankResource.class);

    /**
    * POST issuingbank
    */
    @PostMapping(path="/issuing-bank-entry",produces="application/json",consumes="application/json")
    public String issuingbank(@RequestBody Map<String,String> payload) {
    	
    	String cardNumber=payload.get("cardNumber");
        String cvv=payload.get("cvv");
        String expiry=payload.get("expiry");
        String currencyCode=payload.get("currencyCode");
        String amount=payload.get("amount");
        String merAccNo=payload.get("merAccNo");
        String merIfsc=payload.get("merIfsc");
        
        log.info("Details recieved by Issued bank");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> map=new HashMap<String, String>();
        map.put("cardNumber",cardNumber);
        
        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate=new RestTemplate();
        String fooResourceUrl
            = "http://localhost:8083/api/issuingbank/otp-generation";
        ResponseEntity<String> response = restTemplate.postForEntity(
            fooResourceUrl, request , String.class);
        
        log.info(response.getBody());
        
        return "OTP generated succesfully";
    }
    
    @PostMapping(path="/otp-generation",produces="application/json",consumes="application/json")
    public String issuingbankotpgeneration(@RequestBody Map<String,String> payload) {
    	String cardNumber=payload.get("cardNumber");
    	
    	log.info("OTP Generated Successfully");
    	
    	return "OTP generation successful";
    }
    
    @PostMapping(path="/otp-validation",produces="application/json",consumes="application/json")
    public String otpValidation(@RequestBody Map<String,String> payload) {
    	String otp=payload.get("otp");
    	String mobileNumber=payload.get("mobileNumber");
    	String merAccNo=payload.get("merAccNo");
        String merIfsc=payload.get("merIfsc");
    	
    	log.info("OTP Validation Successful");
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> map=new HashMap<String, String>();
        
        map.put("mobileNumber",mobileNumber);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);
        RestTemplate restTemplate=new RestTemplate();
        String fooResourceUrl
            = "http://localhost:8083/api/issuingbank/debit-funds";
        ResponseEntity<String> response = restTemplate.postForEntity(
            fooResourceUrl, request , String.class);
        
        log.info(response.getBody());
        
        HttpHeaders headers1 = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> map1=new HashMap<String, String>();
        
        map1.put("merAccNo",merAccNo);
        map1.put("merIfsc", merIfsc);
        
        HttpEntity<Map<String, String>> request1 = new HttpEntity<>(map, headers);

        RestTemplate restTemplate1=new RestTemplate();
        String fooResourceUrl1
            = "http://localhost:8081/api/issuingbank/credit-funds";
        ResponseEntity<String> response1 = restTemplate.postForEntity(
            fooResourceUrl, request , String.class);
        
        log.info(response1.getBody());
    	
    	
    	return "Payment Successful";
    }
    
    @PostMapping(path="/debit-funds",produces="application/json",consumes="application/json")
    public String debitFunds(@RequestBody Map<String,String> payload) {
    	String mobileNumber=payload.get("mobileNumber");
    	
    	log.info("Funds debited from issuing bank");
    	return "Funds Debited from Issuing Bank";
    }
}
