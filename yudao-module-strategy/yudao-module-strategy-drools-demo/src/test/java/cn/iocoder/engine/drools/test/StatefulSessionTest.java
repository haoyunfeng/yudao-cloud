package cn.iocoder.engine.drools.test;

import cn.iocoder.engine.drools.model.fact.Counter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author haoyunfeng
 * @create 2024/7/3 16:37
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatefulSessionTest {

    @Test
    public void test(){
        KieServices kieServices = KieServices.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession("stateful-session");

        Counter counter1 = new Counter("count-01", 0);
        FactHandle factHandle = kieSession.insert(counter1);
        // 触发规则
        kieSession.fireAllRules();

        Counter counter2 = new Counter("count-02", 0);
        kieSession.insert(counter2);

        // 这个操作，将会导致规则的重新匹配
//        kieSession.update(factHandle, counter1);

        // 再次触发规则
        kieSession.fireAllRules();

        // 有状态的Session最后一定要调用此方法，防止内存泄漏
        kieSession.dispose();
    }
}
