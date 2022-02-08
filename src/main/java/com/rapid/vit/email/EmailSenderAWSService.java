//package com.rapid.vit.email;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//
//import com.amazonaws.services.dynamodbv2.xspec.S;
//import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
//import com.amazonaws.services.simpleemail.model.Destination;
//import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class EmailSenderAWSService {
//
//    @Value("${smtp-u}")
//    private String accessKey;
//
//    @Value("${smtp-p}")
//    private String secretKey;
//
//    @Value("${smtp-region}")
//    private String region;
//
//    @Value("${smtp-email-from}")
//    private String from;
//
//    @Async
//    public String sendEmail(String region, String from, String [] to, String subject) {
//        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//        com.amazonaws.services.simpleemail.AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
//                .standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
//
//        Destination destination = new Destination();
//        List<String> toAddresses = new ArrayList<String>();
//
//        Collections.addAll(toAddresses, to);
//
//        destination.setToAddresses(toAddresses);
//        SendTemplatedEmailRequest templatedEmailRequest = new SendTemplatedEmailRequest();
//        templatedEmailRequest.withDestination(destination);
//        templatedEmailRequest.withTemplate(templateName);
//        templatedEmailRequest.withTemplateData(templateData);
//        templatedEmailRequest.withSource(from);
//        client.sendTemplatedEmail(templatedEmailRequest);
//        return "email sent";
//    }
//}
