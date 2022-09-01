package com.goddess.rule.executer.handler.nozzle;

import com.goddess.rule.executer.base.BasePo;
import com.goddess.rule.executer.base.Param;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.handler.source.Source;
import com.goddess.rule.executer.meta.MetaClass;

import java.util.List;

public abstract class Nozzle extends BasePo {
    private List<Param> params;

    private String sourceCode;
    private Source source;

    private int complex;
    private String resultDataType;
    private MetaClass resultMetaClass;

    public abstract void setSource(Source source);
    public abstract void extend(DecisionContext context);

}
