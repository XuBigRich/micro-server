package cn.piao888.order.domain;


/**
 * <p>
 *
 * </p>
 *
 * * @author lidong
 * @since 2019-09-04
 */
public class TOrder  {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String orderNo;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Double amount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }



    @Override
    public String toString() {
        return "TOrder{" +
                ", id=" + id +
                ", orderNo=" + orderNo +
                ", userId=" + userId +
                ", commodityCode=" + commodityCode +
                ", count=" + count +
                ", amount=" + amount +
                "}";
    }
}
