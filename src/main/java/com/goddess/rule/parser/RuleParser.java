package com.goddess.rule.parser;

import com.goddess.rule.executer.base.Rule;
import com.goddess.rule.executer.context.RuleConfig;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 09:36
 */
public interface RuleParser {
    Rule parse(String path) throws Exception;
}
