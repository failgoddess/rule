package com.goddess.rule.parser;

import com.goddess.rule.executer.mode.base.bound.*;

public interface BoundParser {
    Compose boundCompose(Object dataObj);
    Bound boundElement(Object dataObj);
    Trim boundTrim(Object dataObj);
    Else boundElse(Object dataObj);
    If boundIf(Object dataObj);
    Choice boundChoice(Object dataObj);
    Where boundWhere(Object dataObj);
    For boundFor(Object dataObj);
}
