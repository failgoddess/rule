public class Main {
    public static void main(String[] args) throws Exception {

        int a = 10;
        Integer b = new Integer(20);
        while (true){
            System.out.println("---");
            Thread.sleep(1000);
        }
        //RuleConfig ruleConfig = new XMLRuleConfigBuilder().build("classpath:demo/rule-config.xml");
        //Rule rule = ruleConfig.getRuleMap().get("RU001");

        //RelationFactory relationFactory = RelationFactory.getInstance();
        //RelationOperation handler = relationFactory.getOperation(Constant.OperationType.EQ);
        //List<String> params = new ArrayList<>();
        //params.add("11");
        //ServiceLoader<RelationOperation> codecSetLoader = ServiceLoader.load(RelationOperation.class);


        //List<String> paths = new ArrayList<>();
        //paths.add("classpath:RULE_RU001.xml");
//        Document document = DocumentHelper.parseText(ResourceUtil.readUtf8Str("classpath:RULE_RU001.xml"));
//        Rule ruleExecute = new Rule();
//        System.out.println(XmlTool.documentToJSONObject(ResourceUtil.readUtf8Str("classpath:RULE_RU001.xml")).toJSONString());
//        System.out.println("----"+handler.execute(0,Constant.DataType.NUMBER,"12",params));
//        System.out.println("----");
    }
}
