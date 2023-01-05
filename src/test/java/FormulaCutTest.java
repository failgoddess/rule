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
        //FormulaNode.CutObj cutObj6 = FormulaNode.cut("{attr1:123}");

        //    %    {    s    i    z    e    (    a    t    t    r    1    :    $    {    s    t    k    I    d    s    }    )    }
        //    0    1    2    3    4    5    6    7    8    9   10   11   12    13   14   15   16   17   18   19   20   21   22   23
        //FormulaNode.CutObj cutObj7 = FormulaNode.cut("%{size(attr1:${stkIds})}{attr:123}");


        FormulaNode.CutObj cutObj8 = FormulaNode.cut("{${stkIds}");
        FormulaNode.CutObj cutObj9 = FormulaNode.cut("${stkIds}}");
        //String name = "select";



        System.out.println("--");
    }
}
