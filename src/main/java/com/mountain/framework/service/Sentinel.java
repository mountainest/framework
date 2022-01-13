package com.mountain.framework.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sentinel {
    public static void main() {
        Sentinel.initFlowRules();
        Sentinel.callResource1();
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

    public static void callResource1() {
        while (true) {
            try (Entry entry = SphU.entry("resource1")) {
                log.info("resource1执行成功");
            } catch (BlockException e) {
                log.error("限流中");
            }

            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                log.error("休眠失败。", e);
            }
        }
    }

    @SentinelResource("resource2")
    public static void callResource2() {
        log.info("resource2执行成功");
    }
}
