package com.example.Hello.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.auth.StsAssumeRoleCredentialsProvider;

@Configuration
public class AwsCredentialConfig {
    private static final Logger logger = LoggerFactory.getLogger(AwsCredentialConfig.class);

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider(){
        try {
            return DefaultCredentialsProvider.builder().build();

        } catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Bean
    public StsClient stsClient(AwsCredentialsProvider awsCredentialsProvider, @Value("${aws.region}") String region){
        return StsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }
    @Bean
    public AwsCredentialsProvider tempCredentialsProvider(StsClient stsClient, @Value("${aws.assume-role.arn}") String roleArn, @Value("${aws.assume-role.session-name}") String sessionName){
        return StsAssumeRoleCredentialsProvider.builder()
                .stsClient(stsClient)
                .refreshRequest(builder -> builder
                        .roleArn(roleArn)
                        .roleSessionName(sessionName)
                        .durationSeconds(3600) // 1시간
                )
                .build();
    }

    @Bean
    public SnsClient snsClient(AwsCredentialsProvider tempCredentialsProvider, @Value("${aws.region}")String region){
        return SnsClient.builder()
                .credentialsProvider(tempCredentialsProvider)
                .region(Region.of(region))
                .build();
    }
}


