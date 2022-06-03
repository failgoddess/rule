package com.goddess.rule.mode.meta;

import java.util.List;

/**
 * 规则对象属相性项枚举
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:12
 */
public class MetaEnumeration {
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型：10固定 20动态
     */
    public String type;
    /**
     * 明细项列表
     */
    public List<Item> items;
    /**
     *加载列表路径
     */
    public String listUrl;
    /**
     *编码路径
     */
    public String codePath;
    /**
     *父编码路径
     */
    public String pCodePath;
    /**
     *名称路径
     */
    public String namePath;

    /**
     * 明细项
     */
    public static class Item{
        /**
         * 编码
         */
        private String code;
        /**
         * 父编码
         */
        private String pCode;
        /**
         * 名称
         */
        private String name;
    }
}
