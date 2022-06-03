import com.goddess.rule.constant.Constant;
import com.goddess.rule.executer.operation.RelationFactory;
import com.goddess.rule.executer.operation.RelationOperation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RelationFactory relationFactory = RelationFactory.getInstance();
        RelationOperation handler = relationFactory.getOperation(Constant.OperationType.EQ);
        List<String> params = new ArrayList<>();
        params.add("11");

        System.out.println("----"+handler.execute(0,Constant.DataType.NUMBER,"12",params));
    }
}
