package cn.iocoder.engine.drools.model.fact;

import lombok.Builder;

/**
 * @author haoyunfeng
 * @create 2024/6/25 15:28
 * @description
 */
@Builder
public class AddressCheckResult {

    /**
     * true:通过校验；false：未通过校验
    */
    private boolean postCodeResult = false;

    public boolean isPostCodeResult() {
        return postCodeResult;
    }

    public void setPostCodeResult(boolean postCodeResult) {
        this.postCodeResult = postCodeResult;
    }
}