package com.goddess.rule.test.meta;

import java.math.BigDecimal;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/7/6 19:24
 */
public class DataInterface {
    public static Store getStoreByCode(String code){
        Store store = new Store();
        Area area = new Area();
        area.code = "QY2001";
        area.name = "京津冀区域";
        area.discount = new BigDecimal(0.8);

        store.code = "1002";
        store.name = "XXX门店";
        store.level = Level.L10;
        store.area = area;
        return store;
    }
}
