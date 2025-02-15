package net.mourou.customerservice.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "customer.param")
public record CustomerConfigPrams (int x,int y){

}
