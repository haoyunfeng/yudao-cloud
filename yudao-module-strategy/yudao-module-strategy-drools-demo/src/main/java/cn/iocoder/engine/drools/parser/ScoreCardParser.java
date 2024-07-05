package cn.iocoder.engine.drools.parser;

import cn.iocoder.engine.drools.enums.OperateEnum;
import cn.iocoder.engine.drools.vo.scorecard.ScoreCardStrategy;

import java.util.Locale;

/**
 * @author haoyunfeng
 * @create 2024/7/5 15:40
 * @description
 */
public class ScoreCardParser extends AbstractParser{

    @Override
    public String parse( ScoreCardStrategy strategy) {
        StringBuilder resultBuilder = new StringBuilder();

        resultBuilder.append("package rules\n");
        strategy.getAttributeVoList().forEach(attribute -> {
            resultBuilder.append("import ").append(attribute.getClassName()).append("\n");
        });
        resultBuilder.append("\n");

        strategy.getAttributeVoList().forEach(attribute -> {
            resultBuilder.append("rule ").append(attribute.getAttributeName()).append("\n");
            resultBuilder.append("agenda-group ").append(strategy.getStrategyName()).append("\n");
            resultBuilder.append("\t").append("salience 10").append("\n");
            resultBuilder.append("\t").append("when").append("\n");
            resultBuilder.append("\t\t");

            String variable = attribute.getClassName().substring(attribute.getClassName().lastIndexOf(".")+1).toLowerCase(Locale.ROOT);

            /* 拼接when条件 start */
            resultBuilder.append("$").append(variable).append(":").append("(");
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
            resultBuilder.append("modify($").append(variable)
                    .append("){").append("set").append(ScoreCardParser.capitalize(attribute.getOutPutField())).append("(")
                    .append(attribute.getScore())
                    .append(")}").append("\n");
        });

        resultBuilder.append("end");



        System.out.println(resultBuilder);
        return resultBuilder.toString();
    }

    /**
     * @description 将首字母修改大写
     * @author haoyunfeng
     * @time 2024/7/5 18:06
     */
    public static String capitalize(String inputString) {
        // get the first character of the inputString
        char firstLetter = inputString.charAt(0);

        // convert it to an UpperCase letter
        char capitalFirstLetter = Character.toUpperCase(firstLetter);

        // return the output string by updating
        //the first char of the input string
        return inputString.replace(inputString.charAt(0), capitalFirstLetter);
    }
}
