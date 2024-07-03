package cn.iocoder.engine.drools.service.impl;

import cn.iocoder.engine.drools.entity.DroolsRule;
import cn.iocoder.engine.drools.manager.DroolsManager;
import cn.iocoder.engine.drools.service.DroolsRuleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author haoyunfeng
 * @create 2024/7/3 14:45
 * @description
 */
@Service
public class DroolsRuleServiceImpl implements DroolsRuleService {

    @Resource
    private DroolsManager droolsManager;

    /**
     * 模拟数据库
     */
    private Map<Long, DroolsRule> droolsRuleMap = new HashMap<>(16);

    @Override
    public List<DroolsRule> findAll() {
        return new ArrayList<>(droolsRuleMap.values());
    }

    @Override
    public void addDroolsRule(DroolsRule droolsRule) {
        droolsRule.validate();
        droolsRule.setCreatedTime(new Date());
        droolsRuleMap.put(droolsRule.getRuleId(), droolsRule);
        droolsManager.addOrUpdateRule(droolsRule);
    }

    @Override
    public void updateDroolsRule(DroolsRule droolsRule) {
        droolsRule.validate();
        droolsRule.setUpdateTime(new Date());
        droolsRuleMap.put(droolsRule.getRuleId(), droolsRule);
        droolsManager.addOrUpdateRule(droolsRule);
    }

    @Override
    public void deleteDroolsRule(Long ruleId, String ruleName) {
        DroolsRule droolsRule = droolsRuleMap.get(ruleId);
        if (null != droolsRule) {
            droolsRuleMap.remove(ruleId);
            droolsManager.deleteDroolsRule(droolsRule, ruleName);
        }
    }

    @Override
    public String fireRule(String kieBaseName, Integer param) {
        return droolsManager.fireRule(kieBaseName, param);
    }
}
