package cn.iocoder.engine.drools.parser;

import cn.iocoder.engine.drools.enums.OperateEnum;
import cn.iocoder.engine.drools.vo.scorecard.ScoreCardStrategy;

import java.util.Locale;

/**
 * @author haoyunfeng
 * @create 2024/7/5 15:40
 * @description
 */
public class ScoreCardParser implements Parser{

    @Override
    public String parse(ScoreCardStrategy strategy) {
        StringBuilder resultBuilder = new StringBuilder();

        /* drl文件头解析 start*/
        resultBuilder.append("package ").append(strategy.getPackageName()).append("\n");
        strategy.getAttributeVoList().forEach(attribute -> {
            resultBuilder.append("import ").append(attribute.getClassName()).append("\n");
        });
        resultBuilder.append("import java.util.Map");
        resultBuilder.append("\n");
        /* drl文件头解析 start*/

        /* 赋分规则drl解析 start*/
        strategy.getAttributeVoList().forEach(attribute -> {
            resultBuilder.append("rule ").append("\"").append(attribute.getAttributeName()).append("\"").append("\n");
//            resultBuilder.append("agenda-group ").append("\"").append(strategy.getStrategyName()).append("\"").append("\n");
            resultBuilder.append("\t").append("salience 10").append("\n");
            resultBuilder.append("\t").append("when").append("\n");
            resultBuilder.append("\t\t");

            String variable = attribute.getClassName().substring(attribute.getClassName().lastIndexOf(".")+1);

            /* 拼接when条件 start */
            resultBuilder.append("$").append(attribute.getAttributeName()).append(":").append(variable).append("(");
            attribute.getConditionList().forEach(condition -> {
                resultBuilder.append(condition.getFieldName());
                resultBuilder.append(" ").append(OperateEnum.getOperatorByCode(condition.getOperator())).append(" ");
                resultBuilder.append(condition.getValue()).append(" ");
                if(attribute.getConditionList().indexOf(condition) != (attribute.getConditionList().size() -1)){
                    resultBuilder.append("&& ");
                }
            });
            resultBuilder.append(")").append("\n");
            /* 拼接when条件 end */

            resultBuilder.append("\t").append("then").append("\n");
            resultBuilder.append("\t\t");
            resultBuilder.append("modify($").append(attribute.getAttributeName())
                    .append("){").append("set").append(ScoreCardParser.capitalize(attribute.getOutPutField())).append("(")
                    .append(attribute.getScore())
                    .append(")}").append("\n");
            resultBuilder.append("end").append("\n");
        });
        /* 赋分规则drl解析 end*/

        /* 计算赋分和值:加权求和 start*/
        resultBuilder.append("rule ").append("\"").append(strategy.getStrategyName()).append("\"").append("\n");
//        resultBuilder.append("agenda-group ").append("\"").append(strategy.getStrategyName()).append("\"").append("\n");
        resultBuilder.append("\t").append("salience 1").append("\n");
        resultBuilder.append("\t").append("when").append("\n");
        strategy.getAttributeVoList().forEach(attribute -> {
            String variable = attribute.getClassName().substring(attribute.getClassName().lastIndexOf(".")+1);
            resultBuilder.append("\t\t");
            resultBuilder.append("$").append(attribute.getAttributeName()).append(":").append(variable).append("(");
            resultBuilder.append("ruleName").append(" == ").append("\"").append(attribute.getAttributeName()).append("\"");
            resultBuilder.append(" && score >0)").append("\n");
        });

        resultBuilder.append("\t\t").append("$map : Map()").append("\n");

        resultBuilder.append("\t").append("then").append("\n");
        resultBuilder.append("\t\t").append("$map.put(\"total\",");
        strategy.getAttributeVoList().forEach(attribute -> {
            resultBuilder.append("$").append(attribute.getAttributeName()).append(".getScore()");
            if(strategy.getAttributeVoList().indexOf(attribute) != (strategy.getAttributeVoList().size() -1)){
                resultBuilder.append(" + ");
            }
        });
        resultBuilder.append(");");

        resultBuilder.append("\n").append("end");
        /* 计算赋分和值:加权求和 end*/

        System.out.println(resultBuilder);
        return resultBuilder.toString();
    }

    /**
     * @description 将首字母修改大写
     * @author haoyunfeng
     * @time 2024/7/5 18:06
     */
    public static String capitalize(String inputString) {
        return inputString.replace(inputString.charAt(0), Character.toUpperCase(inputString.charAt(0)));
    }
}
