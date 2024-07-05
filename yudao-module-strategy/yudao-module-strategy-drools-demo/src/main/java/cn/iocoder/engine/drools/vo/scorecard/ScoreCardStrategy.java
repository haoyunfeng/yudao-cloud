package cn.iocoder.engine.drools.vo.scorecard;

import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * @author haoyunfeng
 * @create 2024/7/5 15:17
 * @description
 */
@Data
@Builder
public class ScoreCardStrategy {
    /** 策略名称 */
    String strategyName;
    /** 是否支持权重 */
    Boolean isWeight;
    /** 属性列表 */
    List<Attribute> attributeVoList;
}
