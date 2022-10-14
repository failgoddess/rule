package demo.tread;

import demo.Area;
import demo.Level;
import demo.Store;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

/**
 * 下单相关
 */
public class CreateOrder {
    //例如在前台下单前（知道用户、门店、金额）需要获取折扣 ，业务规则是 门店等级等于
    public BigDecimal getDiscount(String userCode, String storeCode, BigDecimal amount) {
        Store store = getStore(storeCode);
        if ((store.level == Level.L_10) || (store.area.code == "JJJ")) {
            return new BigDecimal("0.7");
        } else {
            int day = getNewStoreDays();
            if (getDistanceNowDays(store.startTime) > day) {
                return new BigDecimal("0.9");
            } else {
                return new BigDecimal("0.8");
            }
        }
    }

    private int getNewStoreDays() {
        return 365;
    }

    private int getDistanceNowDays(LocalDate history) {
        Period period = Period.between(history, LocalDate.now());
        return period.getDays();
    }

    private Store getStore(String storeCode) {
        Store store = new Store();
        store.code = "1002";
        store.name = "XX门店";
        store.level = Level.L_20;
        store.area = getArea("JJJ");
        return store;
    }

    private Area getArea(String areaCode) {
        Area area = new Area();
        area.code = "JJJ";
        area.name = "京津冀";
        return area;
    }
}
