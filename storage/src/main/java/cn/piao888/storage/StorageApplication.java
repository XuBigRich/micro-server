package cn.piao888.storage;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


//@SpringBootApplication(scanBasePackages = "cn.piao888")
@SpringBootApplication
@EnableResourceServer
@MapperScan({"cn.piao888.storage.mapper"})
@EnableDubbo(scanBasePackages = "cn.piao888.storage")
@EnableDiscoveryClient
public class StorageApplication {


    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }


}
