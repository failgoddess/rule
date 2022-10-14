package com.goddess.rule.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/27 18:40
 */
public class ConstantUtil {
    private static List<String> dataTypes = new ArrayList<>();
    static {
        dataTypes.add(Constant.DataType.NUMBER);
        dataTypes.add(Constant.DataType.STRING);
        dataTypes.add(Constant.DataType.BOLL);
        dataTypes.add(Constant.DataType.TIME_YMD);
        dataTypes.add(Constant.DataType.TIME_YMDHMS);
        dataTypes.add(Constant.DataType.TIME_HMS);
    }

    public static boolean isBaseDataType(String dataType){

        return dataTypes.contains(dataType);
    }
}
