package demo;

import com.alibaba.fastjson.JSONPath;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/11/16 20:05
 */
public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        store.code = "1002";
        store.name = "北京店";
        Area area = new Area();
        area.code = "jjj";
        area.name = "京津冀";
        store.area = area;
        Map<String,Object> data = new HashMap<String, Object>();
        Map<String,Object> area2 = new HashMap<String, Object>();
        area2.put("code","jjj");
        data.put("area",area2);
        String code = JSONPath.eval(data,"$.area.code").toString();
        System.out.println();

    }
}
