package cn.piao888.user.service.impl;

import cn.piao888.common.dto.UserDTO;
import cn.piao888.user.mapper.UserMapper;
import cn.piao888.common.dubbo.UserDubboService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/3/20 3:44 下午
 * @Version 1.0
 */
@DubboService
public class UserServiceImpl implements UserDubboService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
