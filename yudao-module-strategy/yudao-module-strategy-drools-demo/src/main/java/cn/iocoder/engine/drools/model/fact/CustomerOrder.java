package cn.iocoder.engine.drools.model.fact;

/**
 * @author haoyunfeng
 * @create 2024/7/2 09:22
 * @description
 */
public class CustomerOrder {
    /**
     * 购买了几件衣服
     */
    private Integer purchaseQuantity;
    /**
     * 最终打多少折
     */
    private Double discount;

    public CustomerOrder(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "purchaseQuantity=" + purchaseQuantity +
                ", discount=" + discount +
                '}';
    }
}