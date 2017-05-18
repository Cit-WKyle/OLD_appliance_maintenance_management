package com.kyle.ie.auth.config;

import org.springframework.cloud.aws.context.config.annotation.EnableContextCredentials;
import org.springframework.cloud.aws.context.config.annotation.EnableContextRegion;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;
import org.springframework.context.annotation.Configuration;
/*
@Configuration
@EnableRdsInstance(databaseName="${amazon.rds.dbName}", dbInstanceIdentifier="${amazon.rds.dbInstanceId}", username="${amazon.rds.username}", password="${amazon.rds.password}")
@EnableContextCredentials(accessKey="${amazon.aws.accesskey}", secretKey="${amazon.aws.secretkey}")
@EnableContextRegion(region="${amazon.aws.region}")
public class AWSConfig {

}
*/