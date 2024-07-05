package cn.iocoder.engine.drools.enums;

import lombok.Data;
import lombok.Getter;

/**
 *@author haoyunfeng
 *@create 2024/7/5 17:22
 *@description
*/
public enum OperateEnum {
    LESS_THAN("<","less_than", "小于"),
    LESS_THAN_OR_EQUAL_TO("<=","less_than_or_equal_to", "小于等于"),
    GREATER_THAN(">","greater_than", "大于"),
    GREATER_THAN_OR_EQUAL_TO(">=","greater_than_or_equal_to", "大于等于"),
    EQUAL_TO("==","equal_to", "等于"),
    NOT_EQUAL_TO("!=","not_equal_to", "不等于"),
    IN("in", "in","在"),
    NOT_IN("not in","not_in", "不在"),
    IS_NULL("== null","is_null", "为空"),
    IS_NOT_NULL("!= null","is_not_null", "不为空"),
    ;

    @Getter
    private final String operator;
    @Getter
    private final String code;
    @Getter
    private final String name;

    OperateEnum(String operator, String code, String name) {
        this.operator = operator;
        this.code = code;
        this.name = name;
    }

    public static String getOperatorByCode(String code){
        for(OperateEnum operateEnum:OperateEnum.values()){
            if(code.equals(operateEnum.getCode())){
                return operateEnum.getOperator();
            }
        }
        return  null;
    }

}
