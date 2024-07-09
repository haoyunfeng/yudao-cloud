package cn.iocoder.engine.drools.test.strategy;

import cn.iocoder.engine.drools.parser.ScoreCardParser;
import cn.iocoder.engine.drools.vo.scorecard.Attribute;
import cn.iocoder.engine.drools.vo.scorecard.Condition;
import cn.iocoder.engine.drools.vo.scorecard.ScoreCardStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;

import java.util.List;

/**
 * @author haoyunfeng
 * @create 2024/7/5 15:35
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParseDrlTest {

    @Test
    public void parseScoreCardDrl(){
        ScoreCardStrategy scoreCardStrategy = prepareData();
        ScoreCardParser scoreCardParser = new ScoreCardParser();
        String result = scoreCardParser.parse(scoreCardStrategy);
        System.out.println(result);
    }

    private ScoreCardStrategy prepareData(){
        Attribute attribute = Attribute.builder()
                .attributeName("handleRatio")
                .attributeType("bean")
                .className("cn.iocoder.engine.drools.model.fact.Ratio")
                .weight("40")
                .score(Double.valueOf(60))
                .outPutField("score")
                .conditionList(List.of(
                        Condition.builder().fieldName("ratio").operator("greater_than").value("40").build(),
                        Condition.builder().fieldName("ratio").operator("less_than").value("80").build(),
                        Condition.builder().fieldName("type").operator("equal_to").value("1").build()
                ))
                .build();

        Attribute attribute2 = Attribute.builder()
                .attributeName("refluxRatio")
                .attributeType("bean")
                .className("cn.iocoder.engine.drools.model.fact.Ratio")
                .weight("60")
                .score(Double.valueOf(40))
                .outPutField("score")
                .conditionList(List.of(
                        Condition.builder().fieldName("ratio").operator("less_than").value("40").build(),
                        Condition.builder().fieldName("type").operator("equal_to").value("2").build()
                ))
                .build();

        ScoreCardStrategy scoreCardStrategy = ScoreCardStrategy.builder()
                .strategyName("handle-ratio")
                .packageName("rules")
                .isWeight(true)
                .attributeVoList(List.of(attribute,attribute2))
                .build();

        return scoreCardStrategy;
    }
}
