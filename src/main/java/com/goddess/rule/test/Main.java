package com.goddess.rule.test;

import com.goddess.rule.test.meta.DataInterface;
import com.goddess.rule.test.meta.Level;
import com.goddess.rule.test.meta.Store;

import java.math.BigDecimal;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/7/6 19:19
 */
public class Main {
    public static void main(String[] args) {

    }
    //写一个接口根据门店编码获取所在区域的折扣 如果门店等级为甲级则 返回 在所在区域的折扣 的基础上再享受指定折数（折上折）
    public BigDecimal getDiscount(String storeCode,BigDecimal baseDiscount){
        //根据调用某服务的接口 传入数据获取返回数据
        Store store = DataInterface.getStoreByCode(storeCode);
        if(store.level== Level.L10){
            return store.area.discount.multiply(baseDiscount);
        }else {
            return store.area.discount;
        }
    }
}
