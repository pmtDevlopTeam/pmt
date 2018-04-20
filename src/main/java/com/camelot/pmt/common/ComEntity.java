package com.camelot.pmt.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ComEntity<T> {

    public List<String> compareT(T t1, T t2, Class<?> c) {
        List<String> list = new ArrayList<>();
        // 内容没改变直接返回
        if (t1.equals(t2)) {
            return list;
        }
        try {
            // 读取t1和t2中的所有属性
            Field[] fields = c.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                Object value1 = field.get(t1);
                Object value2 = field.get(t2);
                // 判断这两个值是否相等
                if (!isValueEquals(value1, value2)) {
                    list.add(field.getName() + " :{修改前为:  " + value1 + " ;修改后为: " + value2 + "}");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    private boolean isValueEquals(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        if (value1 == null && value2 != null) {
            return false;
        }
        if (value1.equals(value2)) {
            return true;
        }
        return false;

    }
}
