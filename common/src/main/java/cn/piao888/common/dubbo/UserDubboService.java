package cn.piao888.common.dubbo;

import cn.piao888.common.dto.UserDTO;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/3/20 3:45 下午
 * @Version 1.0
 */
public interface UserDubboService {
    UserDTO getUserByUsername(String username);
}
