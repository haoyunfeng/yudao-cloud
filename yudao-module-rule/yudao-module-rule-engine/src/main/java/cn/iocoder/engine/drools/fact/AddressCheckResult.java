package cn.iocoder.engine.drools.fact;

/**
 * @author haoyunfeng
 * @create 2024/6/25 15:28
 * @description
 */
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