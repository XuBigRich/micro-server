package cn.piao888.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = "classpath:dubbo-consumer.xml")
@Configuration
public class DubboConsumerConfig {
}