package com.example.Hello.service;

import com.example.Hello.dto.TopicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.Topic;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AwsService {
    private final SnsClient snsClient;

    public List<TopicDto> listTopics(){
        List<Topic> topics = snsClient.listTopics().topics();

        return topics.stream().map(topic -> new TopicDto(topic.topicArn())).collect(Collectors.toList());
    }
    public String SendMessages(){
        Map<String, MessageAttributeValue> attributes = Map.of(
                "event_type", MessageAttributeValue.builder()
                        .dataType("String")
                        .stringValue("payment_completed")
                        .build(),
                "region", MessageAttributeValue.builder()
                        .dataType("String")
                        .stringValue("us-east-1")
                        .build()
        );

        PublishRequest request = PublishRequest.builder()
                .topicArn("arn:aws:sns:us-east-1:009215122583:testsns")
                .message("Test Message")
                .subject("Test Message")
                .messageAttributes(attributes)
                .build();
        PublishResponse response = snsClient.publish(request);
        return "Message sent Successfully";
    }

}
