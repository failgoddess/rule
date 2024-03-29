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
public class RuleTest {
    @Test
    public void main() throws Exception {
        RuleConfig ruleConfig = new XMLRuleConfigBuilder().build("classpath:demo/rule-config.xml");
        RuleGraph rule = ruleConfig.getRuleGraph("RU001");
        Assert.assertTrue(rule!=null);
        JSONObject dataJson = new JSONObject();
        dataJson.put("buyStoreCode","1002");
        dataJson.put("sendStoreCode","1004");
        dataJson.put("discount","0.88");
        Context context  = ruleConfig.buildContext("RU001");
        DecisionResult decisionResult = rule.decision(context,dataJson);

        System.out.println(decisionResult);
    }
}
