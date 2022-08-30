import com.goddess.rule.executer.base.formula.FormulaNode;
import com.goddess.rule.parser.impl.DefaultFormulaBuilder;
import org.junit.Test;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/5 20:46
 */
public class FormulaHandlerTest {
    @Test
    public void main() throws Exception {
//        JSONObject dataJson = new JSONObject();
//        dataJson.put("buyStoreCode","1002");
//        JSONObject data = FormulaHandler.buildParams(dataJson,null,"(code:${buyStoreCode})");
//        System.out.println(data.toString());
//        内置方法(23,内置方法2(buyStoreCode)).val
        DefaultFormulaBuilder formulaBuilder = new DefaultFormulaBuilder();
        FormulaNode formulaNode1 = formulaBuilder.getFormulaNode("%{内置方法(attr1:23,attr2:%{内置方法2(code:${buyStoreCode})})}.val");
        FormulaNode formulaNode2 = formulaBuilder.getFormulaNode("%{内置方法(attr1:%{内置方法2(code:${buyStoreCode})},attr2:23)}.val");
        FormulaNode formulaNode3 = formulaBuilder.getFormulaNode("%{内置方法(attr1:23,attr2:%{内置方法2(code:${buyStoreCode})},attr3:${buyStoreCode})}.val");
        FormulaNode formulaNode4 = formulaBuilder.getFormulaNode("${buyStoreCode}");
        FormulaNode formulaNode5 = formulaBuilder.getFormulaNode("%{内置方法()}");
        FormulaNode formulaNode6 = formulaBuilder.getFormulaNode("%{内置方法()}.val");
        FormulaNode formulaNode7 = formulaBuilder.getFormulaNode("%{内置方法(attr1:23,attr2:%{内置方法2(code:${buyStoreCode})},attr3:${buyStoreCode},attr4:11)}.val");
        FormulaNode formulaNode8 = formulaBuilder.getFormulaNode("12");
        FormulaNode formulaNode9 = formulaBuilder.getFormulaNode("@{加载器(attr1:23,attr2:%{内置方法2(code:${buyStoreCode})},attr3:${buyStoreCode})}.val");

        System.out.println(formulaNode2);

    }
}
