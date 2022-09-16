package com.goddess.rule.executer.mode.bound;

import com.goddess.rule.executer.context.DecisionContext;

import java.util.ArrayList;
import java.util.List;

public class Compose extends Bound{
    public List<Bound> items;
    public Compose(){
        items = new ArrayList<>();
    }
    @Override
    public String build(DecisionContext context) {
        StringBuilder sb = new StringBuilder();
        for (Bound bound:items) {
            sb.append(bound.build(context));
        }
        return sb.toString();
    }
    public void addItem(Bound item){
        items.add(item);
    }
}
