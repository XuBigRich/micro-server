package cn.piao888.storage.service;

import cn.piao888.common.dto.CommodityDTO;
import cn.piao888.common.dubbo.StorageDubboService;
import cn.piao888.common.response.ObjectResponse;

/**
 * 注意 不可以使用 这种继承接口的方式，因为dubbo注册服务名称是 ，注册的是当类所实现接口的名称
 * 这样，当其他服务调用 StorageDubboService时 在nacos中找不到服务 提供者 ，只能找到ITStorageService
 * 因为其他类肯定是用 StorageDubboService 去调用
 * @author 许鸿志
 * @since 2021/10/9
 */

public interface ITStorageService extends StorageDubboService {

    /**
     * 扣减库存
     */
    ObjectResponse decreaseStorage(CommodityDTO commodityDTO);
}
