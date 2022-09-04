package com.goddess.rule.parser;

import com.goddess.rule.executer.mode.formula.FormulaNode;

public interface FormulaBuilder {
    FormulaNode getFormulaNode(String text);
}
