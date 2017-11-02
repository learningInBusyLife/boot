package com.lick.enums;
/**
 * @ClassName: CharsetType
 * @Description: 加密类型
 * @author: wangze@hsyuntai.com
 * @date: 2016/8/3 17:40
 */
public enum CharsetType {
    UTF_8(1, "UTF-8", "UTF-8"),
    GBK(2, "GBK", "GBK");

    private int value;
    private String name;
    private String desc;

    private CharsetType(int value, String name, String desc) {
        this.value = value;
        this.name = name;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
