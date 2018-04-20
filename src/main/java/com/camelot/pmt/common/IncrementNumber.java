package com.camelot.pmt.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 生成自增长的项目编号
 * 
 * @author qiaodj
 * @date 2018年4月19日
 */
public class IncrementNumber {

    public static String getIncreNum(String str) {
        if (StringUtils.isEmpty(str)) {
            return "01";
        }
        Long parseInt = Long.parseLong(str);
        if (parseInt < 9 && parseInt > 0) {
            String valueOf = String.valueOf(++parseInt);
            return "0" + valueOf;
        } else if (parseInt <= 0) {
            return "01";
        }
        return String.valueOf(++parseInt);
    }
}