package com.goddess.rule.constant;

/**
 * 常量枚举类
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:29
 */
public class Constant {
    /**
     * 是否
     */
    public class YesNo {
        public static final int YES =1;//是
        public static final int No =0;//否
    }


    /**
     * 下一跳类型
     */
    public class NextType {
        public static final int BRANCH =1;//分支
        public static final int RESULT =2;//结果
    }

    /**
     * 数据类型
     */
    public class DataType {
        public static final int NUMBER =10;//数字
        public static final int DATE =20;//时间
        public static final int DATE_YMD =22;//时间-年月日 YYYY-MM-DD
        public static final int DATE_HMS =24;//时间-时分秒 HH:MM:SS
        public static final int DATE_YMDHMS =26;//时间-年月日时分秒 YYYY-MM-DD HH:MM:SS
        public static final int STRING =30;//字符串
        public static final int STRING_IC =35;//不区分大小写的字符串
        public static final int LIST =40;//列表
    }

    public class ResultType {
//        10固定值20节点值30指标值
        public static final int NUMBER =10;//固定值
        public static final int DATE =20;//时间
        public static final int DATE_YMD =22;//时间-年月日 YYYY-MM-DD
    }

    /**
     * 操作符
     */
    public class OperationType{
        public static final String GT ="gt";
        public static final String LE ="le";

        public static final String LT ="lt";
        public static final String GE ="ge";

        public static final String EQ ="eq";
        public static final String NEQ ="neq";

        public static final String EQD ="eqd";
        public static final String NEQD ="neqd";

        public static final String IN ="in";
        public static final String NIN ="nin";

        public static final String EN ="en";
        public static final String NN ="nn";

        public static final String RE ="re";
        public static final String NRE ="nre";

        public static final String CN ="cn";
        public static final String NCN ="ncn";

        public static final String BE ="be";
        public static final String NBE ="nbe";


        public static final String AND ="and";
        public static final String OR ="or";
        public static final String NOT ="not";
        public static final String XOR ="xor";


        public static final String LP ="lp";
        public static final String RP ="rp";
    }


}
