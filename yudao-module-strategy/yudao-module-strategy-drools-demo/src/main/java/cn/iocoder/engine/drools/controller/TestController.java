package cn.iocoder.engine.drools.controller;

import cn.iocoder.engine.drools.model.fact.AddressCheckResult;
import cn.iocoder.engine.drools.model.Address;
import jakarta.annotation.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haoyunfeng
 * @create 2024/6/26 14:09
 * @description
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private KieContainer kieContainer;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/address")
    public void test(String num){
        Address address = new Address();
        address.setPostcode(num);
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
//        kieSession.getQueryResults("Postcode should be filled with exactly 5 numbers");


    }

    /**
     * 生成随机数
     * @param num
     * @return
     */
    public String generateRandom(int num) {
        String chars = "0123456789";
        StringBuffer number=new StringBuffer();
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            number=number.append(chars.charAt(rand));
        }
        return number.toString();
    }
}