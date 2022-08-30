package com.goddess.rule.test.meta;

import java.time.LocalDate;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/7/6 18:56
 */
public class Store {
    //门店编码
    public String code;
    //门店名称
    public String name;
    //开业时间
    public LocalDate startTime;
    //门店等级 10 甲  20 乙 30 丙
    public Level level;
    //门店所属区域
    public Area area;

}
