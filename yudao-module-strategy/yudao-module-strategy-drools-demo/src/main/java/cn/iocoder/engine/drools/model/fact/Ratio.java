package cn.iocoder.engine.drools.model.fact;

import lombok.Builder;
import lombok.Data;

/**
 * @author haoyunfeng
 * @create 2024/7/4 15:33
 * @description
 */
@Data
@Builder
public class Ratio {
    /** 评分类型*/
    private String type;
    /** 赋分*/
    private int ratio;
    /** 评分*/
    private Double score;
    /** 规则名称*/
    private String ruleName;
}
