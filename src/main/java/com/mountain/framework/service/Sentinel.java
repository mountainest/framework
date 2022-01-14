package com.mountain.framework.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Sentinel {
    public void run() {
        while (true){
            this.callResource1();

            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                log.error("休眠失败。", e);
            }
        }
    }

    public static void initFlowRules() {
        List<FlowRule> flowRuleList = new ArrayList<>(2);

        FlowRule rule1 = new FlowRule("resource1");
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        rule1.setCount(0.5);

        flowRuleList.add(rule1);

        FlowRuleManager.loadRules(flowRuleList);
    }

    public void callResource1() {
        ContextUtil.enter("entrance1", "app1");

        try (Entry entry = SphU.entry("resource1")) {
            log.info("resource1执行成功");
        } catch (BlockException e) {
            log.error("限流中");
        }

        ContextUtil.exit();
    }

    @SentinelResource("resource2")
    public void callResource2() {
        // 注解方式未搞定
        log.info("resource2执行成功");
    }
}
