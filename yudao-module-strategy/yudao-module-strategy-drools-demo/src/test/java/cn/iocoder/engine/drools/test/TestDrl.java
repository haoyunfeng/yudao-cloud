package cn.iocoder.engine.drools.test;

import cn.iocoder.engine.drools.model.Address;
import cn.iocoder.engine.drools.model.fact.AddressCheckResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author haoyunfeng
 * @create 2024/6/28 14:47
 * @description
 */
//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestDrl {

    @Autowired
    private KieContainer kieContainer;

    @Test
    public void testJudge(){
        Address address = new Address();
        address.setPostcode("hahaha");
        KieSession kieSession = kieContainer.newKieSession();

        AddressCheckResult result = AddressCheckResult.builder().build();
        kieSession.insert(address);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        if(result.isPostCodeResult()){
            System.out.println("命中规则");
        }else {
            System.out.println("未命中规则");
        }
    }
}
