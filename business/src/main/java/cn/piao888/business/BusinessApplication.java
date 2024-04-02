package cn.piao888.business;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
//====================SPI 问题====================
//使用下面这种方式 ，springboot不会默认加载common:client-auth-common、:common:common-core两个项目的resources文件夹
//    implementation project(":common:client-auth-common")
//    implementation project(":common:common-core")
//如果将 common:client-auth-common、:common:common-core两个项目打包发不到本地仓库中，resources文件夹中META-INF的spring.factories和dubbo文件夹
//都将会被处理
//    implementation 'cn.piao888:client-auth-common:0.0.1-SNAPSHOT'
//    implementation 'cn.piao888:common-core:0.0.1-SNAPSHOT'

//@SpringBootApplication(scanBasePackages = "cn.piao888")
@SpringBootApplication
@Data
public class BusinessApplication {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${nacos.discovery.server-addr}")
    private String address;
    @NacosInjected
    private NamingService namingService;

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }


    @PostConstruct
    public void registerService() throws NacosException {
        namingService.registerInstance(applicationName, address, serverPort);
    }

}
