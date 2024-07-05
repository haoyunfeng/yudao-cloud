package cn.iocoder.engine.drools.vo.scorecard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author haoyunfeng
 * @create 2024/7/5 15:19
 * @description
 */
@Data
@Builder
public class Attribute {
    /** 名称 */
    private String attributeName;
    /** 类型 枚举值:bean;map*/
    private String attributeType;
    /** class名称 */
    private String className;
    /** class全路径 */
    private String attributeClass;
    /** 权重 */
    private String weight;
    /** 评分 */
    private Double score;
    /** 结果分 */
    private String outPutField;
    /** 条件 */
    private List<Condition> conditionList;
}
