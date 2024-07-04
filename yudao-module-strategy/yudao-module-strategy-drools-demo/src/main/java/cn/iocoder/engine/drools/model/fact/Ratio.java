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

    private String type;

    private int ratio;

    private int score;
}
