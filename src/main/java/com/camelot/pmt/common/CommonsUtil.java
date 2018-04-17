package com.camelot.pmt.common;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: CommonsUtil
 * @Description: json转换对象工具
 * @author lixiaokang
 * @date 2018年4月12日 下午5:47:29
 */
public class CommonsUtil {

    private static ObjectMapper om = new ObjectMapper();
    static {
        om.setSerializationInclusion(Include.NON_NULL);
    }

    // 对象转JSOn
    public static String objectToJson(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // json转对象
    public static <T> T jsonToObject(String json, Class<T> javaType) {
        T t = null;
        try {
            t = om.readValue(json, javaType);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 获取两个日期之间的工作日天数
     * 
     * @param startDate
     * @param endDate
     * @return 间隔的天数
     */
    @SuppressWarnings("deprecation")
    public static int getDutyDays(Date startDate, Date endDate) {
        int result = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
        while (startDate.compareTo(endDate) <= 0) {
            if (startDate.getDay() != 6 && startDate.getDay() != 0)
                result++;
            startDate.setDate(startDate.getDate() + 1);
        }

        return result;
    }

}
