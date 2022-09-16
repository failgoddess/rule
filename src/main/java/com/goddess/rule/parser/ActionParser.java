package com.goddess.rule.parser;

import com.goddess.rule.executer.mode.action.Action;

/**
 * 行为构建器
 */
public interface ActionParser {
    Action parse(Object dataObj);
}
