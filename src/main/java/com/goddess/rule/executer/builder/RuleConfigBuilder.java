package com.goddess.rule.executer.builder;

import com.goddess.rule.executer.context.RuleConfig;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 16:16
 */
public abstract class RuleConfigBuilder {
    public abstract RuleConfig build(String configPath) throws Exception;
}
