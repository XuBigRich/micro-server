package cn.piao888.storage.service;

import cn.piao888.common.dto.CommodityDTO;
import cn.piao888.common.response.ObjectResponse;

/**
 * @author 许鸿志
 * @since 2021/10/9
 */
public interface ITStorageService {

    /**
     * 扣减库存
     */
    ObjectResponse decreaseStorage(CommodityDTO commodityDTO);
}
