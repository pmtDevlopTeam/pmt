package com.camelot.pmt.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class compareBeanAttr {

    public static <T> T compareBeanAttr(Class<T> beanClass, T newBean, T oldBean, String... ignoreAttrNames)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchFieldException {
        String str = "";
        // 得到实体类的所有方法
        Method[] beanMethods = beanClass.getDeclaredMethods();
        // 得到所有名称为: “getXxx”方法和“setXxx”方法，“Xxx”为属性名首字大写后的名称。
        final String preGet = "get";
        final String preSet = "set";
        final List<Method> getMethods = new ArrayList<Method>();
        final List<Method> setMethods = new ArrayList<Method>();
        for (Method method : beanMethods) {
            String methodName = method.getName();
            // 得到get方法
            if (preGet.equals(methodName.substring(0, preGet.length()))) {
                getMethods.add(method);
            }
            // 得到set方法
            else if (preSet.equals(methodName.substring(0, preSet.length()))) {
                setMethods.add(method);
            }
        }

        /**
         * 判断newBean: 1.newBean对应的get方法的得到的属性值是否为空（null或""）
         * 2.比较newBean和oldBean对应get方法得到的属性值是否相同
         */
        for (Method getMethod : getMethods) {
            Object newValue = getMethod.invoke(newBean);
            Object oldValue = getMethod.invoke(oldBean);
            // 得到get方法名
            String getMethodName = getMethod.getName();
            // 得到首字母为大写的属性名
            String lastName = getMethodName.substring(preSet.length());
            // 把首字转成小写后称为属性名
            String attrName = lastName.substring(0, 1).toLowerCase() + lastName.substring(1);
            Field field = beanClass.getDeclaredField(attrName);
            Annotation annotation = field.getAnnotation(XmlElement.class);
            if (annotation != null && newValue != null) {
                XmlElement xmlElement = (XmlElement) annotation;
                if (xmlElement.name().equals("default")) {
                    System.out.println("属性【" + attrName + "】注解使用的name是默认值: " + xmlElement.name());
                } else {
                    if (oldValue.equals(newValue)) {
                        continue;
                    }
                    if (null == oldValue) {
                        str += xmlElement.name() + ":" + "    更改为     " + newValue + "\t\n";
                    } else {
                        str += xmlElement.name() + ":" + "  由    " + oldValue + "    更改为      " + newValue + "\t\n";
                    }

                }
            }
        }
        return (T) str;
    }
}
