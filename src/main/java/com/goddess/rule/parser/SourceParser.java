package com.goddess.rule.parser;

import com.goddess.rule.executer.handler.source.Source;

public interface SourceParser {
    Source parse(Object dataObj);
}
