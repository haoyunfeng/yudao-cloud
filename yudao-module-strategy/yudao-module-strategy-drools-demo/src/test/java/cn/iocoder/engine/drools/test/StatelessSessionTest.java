package cn.iocoder.engine.drools.test;

import cn.iocoder.engine.drools.model.fact.Counter;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haoyunfeng
 * @create 2024/7/3 16:14
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatelessSessionTest {

    @Test
    public void test(){
        KieServices kieServices = KieServices.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();

        StatelessKieSession session = kieClasspathContainer.newStatelessKieSession("stateless-session");

        // 设置一个session级别的全局变量
        session.setGlobal("username", "huan.fu");

        Counter counter01 = new Counter("count-01", 0);
        // 第一调用execute方法
        session.execute(counter01);

        List<Command<?>> commands = new ArrayList<>();
        Counter counter02 = new Counter("count-02", 0);

        commands.add(CommandFactory.newInsert(counter02));
        BatchExecutionCommand batchExecutionCommand = CommandFactory.newBatchExecution(commands);
        // 第二次调用execute方法
        session.execute(batchExecutionCommand);

    }
}
