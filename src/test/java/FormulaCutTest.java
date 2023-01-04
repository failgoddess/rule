import com.goddess.rule.executer.mode.base.formula.FormulaNode;
import org.junit.Test;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/5 20:46
 */
public class FormulaCutTest {
    @Test
    public void main() throws Exception {
        //RuleConfig ruleConfig = new XMLRuleConfigBuilder().build("classpath:demo/rule-config.xml");

        //FormulaNode.CutObj cutObj1 = FormulaNode.cut("txt");
        //FormulaNode.CutObj cutObj2 = FormulaNode.cut("%{add()}");

        //FormulaNode.CutObj cutObj3 = FormulaNode.cut("%{add(attr1:1)}");
        //FormulaNode.CutObj cutObj4 = FormulaNode.cut("%{add(attr1:1,attr2:2)}");
        //FormulaNode.CutObj cutObj5 = FormulaNode.cut("\\%{add()\\}");


        FormulaNode.CutObj cutObj6 = FormulaNode.cut("{attr1:123}");
        //String name = "select";



        System.out.println("--");
    }
}
