package cn.piao888.common.dubbo;


import cn.piao888.common.dto.CommodityDTO;
import cn.piao888.common.response.ObjectResponse;

/**
 * @Author: lidong
 * @Description 库存服务
 * @Date Created in 2019/9/5 16:22
 */
public interface StorageDubboService {

    /**
     * 扣减库存
     */
    ObjectResponse decreaseStorage(CommodityDTO commodityDTO);
}
