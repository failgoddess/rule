package com.goddess.rule.constant;

/**
 * 常量枚举类
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:29
 */
public class Constant {
    public class DateFormatter {
        public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        public static final String YYYY_MM_DD = "yyyy-MM-dd";
        public static final String HH_MM_SS = "HH:mm:ss";
    }
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
        //数值
        public static final int NUMBER=10;
        //字符串
        public static final int STRING=20;
        //不区分大小写的字符串
        public static final int STRING_IC=22;
        //布尔
        public static final int BOLL=30;
        //时间年月日  yyyy-MM-dd
        public static final int TIME_YMD=40;
        //时间年月日时分秒 yyyy-MM-dd HH:mm:ss
        public static final int TIME_YMDHMS=42;
        //时间时分秒  HH:mm:ss
        public static final int TIME_HMS=44;

        public static final int LIST =50;//列表
    }


    public class ResultType {
        //        10固定值20节点值30指标值
        public static final int NUMBER =10;//固定值
        public static final int DATE =20;//时间
        public static final int DATE_YMD =22;//时间-年月日 YYYY-MM-DD
    }
    public class ThresholdType {
        public static final int FIXED =10;//固定值
    }

    /**
     * 操作符
     */
    public class OperationType{
        public static final String GT ="gt";//大于
        public static final String LE ="le";//小于等于

        public static final String LT ="lt";//小于
        public static final String GE ="ge";//大于等于

        public static final String EQ ="eq";//等于
        public static final String NEQ ="neq";//不等于

        public static final String EQD ="eqd";//等于(不区分大小写)
        public static final String NEQD ="neqd";//不等于(不区分)

        public static final String IN ="in";//在集合
        public static final String NIN ="nin";//不在集合

        public static final String EN ="en";//为空
        public static final String NN ="nn";//不为空

        public static final String RE ="re";//正则
        public static final String NRE ="nre";//不正则

        public static final String CN ="cn";//包含
        public static final String NCN ="ncn";//不包含

        public static final String BE ="be";//介于(闭区间)
        public static final String NBE ="nbe";//不介于(开区间)

        public static final String BED ="bed";//介于(开区间)
        public static final String NBED ="nbed";//介于(开区间)

        public static final String BEL ="bel";//介于(前闭后开)
        public static final String NBEL ="nbel";//介于(前闭后开)

        public static final String BER ="bel";//介于(前开后闭)
        public static final String NBER ="nbel";//介于(前开后闭)


        public static final String AND ="and";//短路与
        public static final String OR ="or";//短路或
        public static final String NOT ="not";//非
        public static final String XOR ="xor";//异或


        public static final String LP ="lp";//左括号
        public static final String RP ="rp";//右括号
    }


}
