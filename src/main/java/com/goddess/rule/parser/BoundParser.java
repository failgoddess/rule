package com.goddess.rule.parser;

import com.goddess.rule.executer.mode.base.bound.*;

public interface BoundParser<T> {
    Compose boundCompose(T dataObj);
    Bound boundElement(T dataObj);
    Trim boundTrim(T dataObj);
    Else boundElse(T dataObj);
    If boundIf(T dataObj);
    Choice boundChoice(T dataObj);
    Where boundWhere(T dataObj);
    For boundFor(T dataObj);
    Str boundStr(T dataObj);
    Formula boundFormula(T dataObj);
}
