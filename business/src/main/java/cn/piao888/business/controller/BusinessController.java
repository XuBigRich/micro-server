package cn.piao888.business.controller;

import cn.piao888.business.service.BusinessService;
import cn.piao888.common.dto.BusinessDTO;
import cn.piao888.common.response.ObjectResponse;
import cn.piao888.oauth2.utils.CurrentUserVo;
import cn.piao888.oauth2.utils.SecurityUtils;
import cn.piao888.oauth2.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author: lidong
 * @Description Dubbo业务执行入口
 * @Date Created in 2019/1/14 17:15
 */
@RestController
@RequestMapping("/business/dubbo")
@Slf4j
public class BusinessController {


    @Autowired
    private BusinessService businessService;

    @GetMapping("/loginSuccessful")
    public void loginSuccessful() {
        CurrentUserVo authentication = SessionUtil.get();
        System.out.printf("请求成功");
    }

    /**
     * 模拟用户购买商品下单业务逻辑流程
     *
     * @Param:
     * @Return:
     */
    @PostMapping("/buy")
    ObjectResponse handleBusiness(@RequestBody BusinessDTO businessDTO) {
        CurrentUserVo authentication = SessionUtil.get();
        log.info("请求参数：{}", businessDTO.toString());
        return businessService.handleBusiness(businessDTO);
    }

    /**
     * 模拟用户购买商品下单业务异常逻辑流程
     *
     * @Param:
     * @Return:
     */
    @PostMapping("/buy2")
    @PreAuthorize("hasAuthority('web')")
    ObjectResponse handleBusiness2(@RequestBody BusinessDTO businessDTO) {
        log.info("请求参数：{}", businessDTO.toString());
        return businessService.handleBusiness2(businessDTO);
    }
}
