/**
 * 描述:
 * 包名:com.xiangweihui.manager.util.system
 * 版本信息: 版本1.0
 * 日期:2019/1/315:47
 * Copyright
 */
package cn.piao888.common.utils;


import cn.piao888.common.vo.CurrentUserVo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe:处理用户公参的操作
 */
@Slf4j
public class SessionUtil {
    private SessionUtil() {
    }
    /**
     * 专用
     */
    /**
     * 一个线程Map，用来存放线程和其对应的变量副本，这里可以放泛型
     */
    private static Map<Thread, CurrentUserVo> threadMap = Collections.synchronizedMap(new HashMap());

    public static void set(CurrentUserVo currentUserVo) {
        if (null == currentUserVo) {
            log.info("公共参数更新：空参数");
            return;
        }
        log.info("公共参数更新：" + JSONObject.toJSONString(currentUserVo));
        threadMap.put(Thread.currentThread(), currentUserVo);
    }


    public synchronized static CurrentUserVo get() {
        CurrentUserVo currentUserVo = threadMap.get(Thread.currentThread());
        if (currentUserVo == null) {
            currentUserVo = new CurrentUserVo();
        }
        return currentUserVo;
    }

    public static void remove() {
        threadMap.remove(Thread.currentThread());
    }

    //得到数量
    public static Integer getSize() {
        return threadMap.size();
    }


}