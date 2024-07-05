package cn.iocoder.engine.drools.vo.scorecard;

import lombok.Builder;
import lombok.Data;

/**
 * @author haoyunfeng
 * @create 2024/7/5 15:22
 * @description
 */
@Data
@Builder
public class Condition {
    /** 名称 */
    private String fieldName;
    /** 操作符;枚举：
     * greater:大于；
     * less:小于；
     * equal:等于；
     * notEqual:不等于；
     */
    private String operator;
    /** 条件值 */
    private String value;
}
