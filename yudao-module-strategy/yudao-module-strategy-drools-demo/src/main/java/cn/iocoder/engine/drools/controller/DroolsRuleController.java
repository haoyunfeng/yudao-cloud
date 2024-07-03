package cn.iocoder.engine.drools.controller;

import cn.iocoder.engine.drools.entity.DroolsRule;
import cn.iocoder.engine.drools.manager.DroolsManager;
import cn.iocoder.engine.drools.service.DroolsRuleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author haoyunfeng
 * @create 2024/7/3 14:52
 * @description
 */
@RestController
@RequestMapping("/drools/rule")
public class DroolsRuleController {

    @Resource
    private DroolsRuleService droolsRuleService;

    @GetMapping("findAll")
    public List<DroolsRule> findAll() {
        return droolsRuleService.findAll();
    }

    @PostMapping("add")
    public String addRule(@RequestBody DroolsRule droolsRule) {
        droolsRuleService.addDroolsRule(droolsRule);
        return "添加成功";
    }

    @PostMapping("update")
    public String updateRule(@RequestBody DroolsRule droolsRule) {
        droolsRuleService.updateDroolsRule(droolsRule);
        return "修改成功";
    }

    @PostMapping("deleteRule")
    public String deleteRule(Long ruleId, String ruleName) {
        droolsRuleService.deleteDroolsRule(ruleId, ruleName);
        return "删除成功";
    }

    @GetMapping("fireRule")
    public String fireRule(String kieBaseName, Integer param) {
        return droolsRuleService.fireRule(kieBaseName, param);
    }
}
