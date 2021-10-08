package cn.piao888.business.service;

import cn.piao888.common.dto.BusinessDTO;
import cn.piao888.common.response.ObjectResponse;

/**
 * @author 许鸿志
 * @since 2021/10/8
 */
public interface BusinessService {

    /**
     * 出处理业务服务
     * @param businessDTO
     * @return
     */
    ObjectResponse handleBusiness(BusinessDTO businessDTO);


    /**
     * 出处理业务服务，出现异常回顾
     * @param businessDTO
     * @return
     */
    ObjectResponse handleBusiness2(BusinessDTO businessDTO);
}
