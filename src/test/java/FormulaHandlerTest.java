import com.goddess.rule.executer.base.formula.FormulaNode;
import com.goddess.rule.executer.handler.FormulaHandler;
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

        FormulaNode formulaNode1 = FormulaHandler.getFormulaNode("%{内置方法(attr1:23,attr2:%{内置方法2(code:${buyStoreCode})})}.val");
        FormulaNode formulaNode2 = FormulaHandler.getFormulaNode("%{内置方法(attr1:%{内置方法2(code:${buyStoreCode})},attr2:23)}.val");
        FormulaNode formulaNode3 = FormulaHandler.getFormulaNode("%{内置方法(attr1:23,attr2:%{内置方法2(code:${buyStoreCode})},attr3:${buyStoreCode})}.val");
        FormulaNode formulaNode4 = FormulaHandler.getFormulaNode("${buyStoreCode}");
        FormulaNode formulaNode5 = FormulaHandler.getFormulaNode("%{内置方法()}");
        FormulaNode formulaNode6 = FormulaHandler.getFormulaNode("%{内置方法()}.val");
        FormulaNode formulaNode7 = FormulaHandler.getFormulaNode("%{内置方法(attr1:23,attr2:%{内置方法2(code:${buyStoreCode})},attr3:${buyStoreCode},attr4:11)}.val");
        FormulaNode formulaNode8 = FormulaHandler.getFormulaNode("12");

        System.out.println(formulaNode2);
//        System.out.println(FormulaHandler.cut("%{内置方法(attr1:23,attr2:%{内置方法2(code:${buyStoreCode})})}.val"));
        /**
         * %{内置方法(attr1:23,attr2:%{内置方法2(code:${buyStoreCode})})}.val
         * %{                                                        }.val
         *   内置方法(                                               )
         *           attr1:23,attr2:%{                             }
         *                            内置方法2(                   })
         *                                         ${buyStoreCode}
         */
    }
}
