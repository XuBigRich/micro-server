package cn.piao888.business;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.spring.context.annotation.EnableNacos;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Role;

import javax.annotation.PostConstruct;

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
        namingService.registerInstance(applicationName,address, serverPort);
    }

}
