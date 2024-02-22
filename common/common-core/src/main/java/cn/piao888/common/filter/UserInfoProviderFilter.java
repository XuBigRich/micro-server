package cn.piao888.common.filter;

import cn.piao888.common.constant.SecurityConstants;
import cn.piao888.common.utils.SessionUtil;
import cn.piao888.common.vo.CurrentUserVo;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

/**
 *  dubbo间 调用  取值用
 */
@Slf4j
//在filter实现类添加@Activate(group = “provider"注解，并指定服务提供者还是消费者端生效
@Activate(group = "provider")
public class UserInfoProviderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        final String s = invocation.getAttachments().get(SecurityConstants.USER_INFO);
        final CurrentUserVo userInfo = JSONObject.parseObject(s, CurrentUserVo.class);
        SessionUtil.set(userInfo);
        return invoker.invoke(invocation);
    }
}

