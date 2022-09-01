package com.goddess.rule.parser;

import com.goddess.rule.executer.handler.nozzle.Nozzle;

public interface NozzleParser {
    Nozzle parse(Object dataObj);
}
