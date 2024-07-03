package cn.iocoder.engine.drools.model.fact;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author haoyunfeng
 * @create 2024/7/3 16:19
 * @description
 */
@Data
@AllArgsConstructor
public class Counter {
    /**
     * 名称
     */
    private String name;
    /**
     * 计数
     */
    private Integer cnt;
}
