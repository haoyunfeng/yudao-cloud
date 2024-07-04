package cn.iocoder.engine.drools.test;

import cn.iocoder.engine.drools.model.fact.Ratio;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @author haoyunfeng
 * @create 2024/7/4 15:59
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScoreCardTest {

    @Autowired
    private KieContainer kieContainer;

    @Test
    public void testScoreCard(){
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.addEventListener(new DebugRuleRuntimeEventListener());

        //办件比例
        Ratio ratio1 = Ratio.builder().ratio(65).type("1").build();
        kieSession.insert(ratio1);
        //回流比例
        Ratio ratio2 = Ratio.builder().ratio(35).type("2").build();
        kieSession.insert(ratio2);
        //赋分值
        HashMap map = new HashMap();
        map.put("total", "");
        kieSession.insert(map);
        kieSession.getAgenda().getAgendaGroup("ratio").setFocus();
        kieSession.fireAllRules(new AgendaFilter() {
            @Override
            public boolean accept(Match match) {
                String ruleName = match.getRule().getName();
                return ruleName.startsWith("handle-ratio");
            }
        });
        kieSession.dispose();
        System.err.println("ratio1结果:" + ratio1.getScore());
        System.err.println("ratio2结果:" + ratio2.getScore());
        System.err.println("total结果:" + map.get("total"));
    }
}
