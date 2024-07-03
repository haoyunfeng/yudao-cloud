package cn.iocoder.engine.drools.test;

import cn.iocoder.engine.drools.model.fact.CustomerOrder;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @author haoyunfeng
 * @create 2024/7/2 09:23
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StateTest {

    @Resource
    private KieContainer kieContainer;

//    @Resource
//    private KieSession kieSession;

    @Test
    public void testStatelessSession(){
//        KieServices kieServices = KieServices.get();
//        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        // 获取kie services
        StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
        // 创建一个对象，可以理解为 Fact对象，即事实对象
        CustomerOrder customerOrder = new CustomerOrder(2);
        // 添加监听器，便于观察日志
        kieSession.addEventListener(new DebugRuleRuntimeEventListener());
        kieSession.addEventListener(new DebugAgendaEventListener());
        // 无状态的session只需要执行 execute 方法即可。
        kieSession.execute(customerOrder);

        System.err.println("通过规则后，获取到的折扣为:" + customerOrder.getDiscount());

    }

    @Test
    public void statefulSessionTest() {
        // 创建一个对象，可以理解为 Fact对象，即事实对象
        CustomerOrder customerOrder = new CustomerOrder(4);
        // 添加监听器，便于观察日志
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.addEventListener(new DebugRuleRuntimeEventListener());
        // 将customerOrder对象加入到工作内存中
        HashMap map = new HashMap();
        map.put("test", "123");
        kieSession.insert(map);
//        kieSession.insert(customerOrder);

        // 触发所有的规则，如果只想触发指定的规则，则使用fireAllRules(AgendaFilter agendaFilter)方法
        kieSession.fireAllRules();

        // 有状态的session一定需要调用dispose方法
        kieSession.dispose();

//        System.err.println("通过规则后，获取到的折扣为:" + customerOrder.getDiscount());
        System.err.println("map结果:" + map.get("test"));
    }
}
