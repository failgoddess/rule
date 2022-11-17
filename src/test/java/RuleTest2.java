import com.alibaba.fastjson.JSONObject;
import com.goddess.rule.executer.context.Context;
import com.goddess.rule.executer.context.RuleConfig;
import com.goddess.rule.executer.mode.rule.graph.DecisionResult;
import com.goddess.rule.executer.mode.rule.graph.RuleGraph;
import com.goddess.rule.parser.impl.xml.XMLRuleConfigBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 16:20
 */
public class RuleTest2 {
    @Test
    public void main() throws Exception {
        RuleConfig ruleConfig = new XMLRuleConfigBuilder().build("classpath:demo/rule-config.xml");
        RuleGraph rule = ruleConfig.getRuleGraph("RU003");
        Assert.assertTrue(rule!=null);
        JSONObject dataJson = new JSONObject();
        dataJson.put("attr1","陆佳1");
        dataJson.put("attr2",20);
        Context context  = ruleConfig.buildContext("RU003");
        DecisionResult decisionResult = rule.decision(context,dataJson);

        System.out.println(decisionResult);
    }
}
