package cn.iocoder.engine.drools.service;

import cn.iocoder.engine.drools.entity.DroolsRule;

import java.util.List;

/**
 * @author haoyunfeng
 * @create 2024/7/3 14:44
 * @description
 */
public interface DroolsRuleService {

    /**
     * 从数据库中加载所有的drools规则
     */
    List<DroolsRule> findAll();

    /**
     * 添加drools规则
     */
    void addDroolsRule(DroolsRule droolsRule);

    /**
     * 修改drools 规则
     */
    void updateDroolsRule(DroolsRule droolsRule);

    /**
     * 删除drools规则
     */
    void deleteDroolsRule(Long ruleId, String ruleName);

    String fireRule(String kieBaseName, Integer param);
}
