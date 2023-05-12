package cn.piao888.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 * Mapper 接口
 * </p>
 * <p>
 * * @author lidong
 *
 * @since 2019-09-04
 */
@Mapper
public interface TAccountMapper {

    int decreaseAccount(@Param("userId") Long userId, @Param("amount") BigDecimal amount);
}
