package cn.piao888;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.Data;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Data
@EnableDubbo(scanBasePackages = "cn.piao888.account")
public class AccountApplication {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${nacos.discovery.server-addr}")
    private String address;
    @NacosInjected
    private NamingService namingService;

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }


    @PostConstruct
    public void registerService() throws NacosException {
        namingService.registerInstance(applicationName, address, serverPort);
    }


}
