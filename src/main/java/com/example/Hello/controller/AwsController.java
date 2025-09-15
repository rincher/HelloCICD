package com.example.Hello.controller;

import com.example.Hello.dto.TopicDto;
import com.example.Hello.service.AwsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sns.model.Topic;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AwsController {
    private final AwsService awsService;

    @GetMapping("/aws/sns/list")
    public List<TopicDto> getList(){
        return awsService.listTopics();
    }
    @PostMapping("/aws/sns/send")
    public String sendMessage(){
        return awsService.SendMessages();
    }
}
