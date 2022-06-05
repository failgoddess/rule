import com.alibaba.fastjson2.JSONObject;
import com.goddess.rule.executer.base.DecisionResult;
import com.goddess.rule.executer.base.Rule;
import com.goddess.rule.executer.context.DecisionContext;
import com.goddess.rule.executer.context.RuleConfig;
import com.goddess.rule.parser.impl.XMLRuleConfigBuilder;
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
        RuleConfig ruleConfig = new XMLRuleConfigBuilder().build("classpath:rule-config.xml");
        Rule rule = ruleConfig.getRuleMap().get("RU001");
        Assert.assertTrue(rule!=null);
        DecisionContext decisionContext  = ruleConfig.getDecisionContext();
        JSONObject dataJson = new JSONObject();
        DecisionResult decisionResult = rule.decision(decisionContext,dataJson);
        System.out.println(decisionResult);
    }
}
