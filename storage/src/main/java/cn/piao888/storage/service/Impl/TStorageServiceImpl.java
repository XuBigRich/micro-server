package cn.piao888.storage.service.Impl;

import cn.piao888.common.dto.CommodityDTO;
import cn.piao888.common.dubbo.StorageDubboService;
import cn.piao888.common.enums.RspStatusEnum;
import cn.piao888.common.response.ObjectResponse;
import cn.piao888.storage.mapper.TStorageMapper;
import cn.piao888.storage.service.ITStorageService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 许鸿志
 * @since 2021/10/9
 */
@DubboService(version = "1.0.1")
@Slf4j
public class TStorageServiceImpl implements StorageDubboService {
    @Autowired
    private TStorageMapper tStorageMapper;

    @Override
    public ObjectResponse decreaseStorage(CommodityDTO commodityDTO) {
        log.info("开始全局事务，XID = " + RootContext.getXID());
        int storage = tStorageMapper.decreaseStorage(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        ObjectResponse<Object> response = new ObjectResponse<>();
        if (storage > 0) {
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.FAIL.getCode());
        response.setMessage(RspStatusEnum.FAIL.getMessage());
        return response;
    }
}
