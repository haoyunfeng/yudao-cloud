package cn.iocoder.engine.drools.util;

import lombok.Getter;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author haoyunfeng
 * @create 2024/6/25 15:31
 * @description
 */
public class KieUtils {
    @Getter
    private static KieContainer kieContainer;

    @Getter
    private static KieSession kieSession;

    public static void setKieContainer(KieContainer kieContainer) {
        KieUtils.kieContainer = kieContainer;
        kieSession = kieContainer.newKieSession();
    }

    public static void setKieSession(KieSession kieSession) {
        KieUtils.kieSession = kieSession;
    }

    public static KieServices getKieServices() {
        return KieServices.Factory.get();
    }
}