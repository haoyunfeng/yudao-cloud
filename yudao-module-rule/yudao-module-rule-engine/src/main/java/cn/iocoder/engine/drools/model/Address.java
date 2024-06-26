package cn.iocoder.engine.drools.model;

import lombok.Data;

/**
 * @author haoyunfeng
 * @create 2024/6/25 15:06
 * @description
 */
@Data
public class Address {
    /**
     * 邮编
     */
    private Integer postcode;
    /**
     * 街道
     */
    private String street;
    /**
     * 省份
     */
    private String state;
}
