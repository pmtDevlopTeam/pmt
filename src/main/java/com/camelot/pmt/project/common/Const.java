package com.camelot.pmt.project.common;

/**
 * @Package: com.camelot.pmt.project.common
 * @ClassName: Const
 * @Description: 常量类
 * @author: xueyj
 * @date: 2018-04-17 14:06
 */
public class Const {
    public enum VersionStatusEnum{
        DEVELOPMENT("01","开发"),
        TEST("02","测试");

        VersionStatusEnum(String code,String value) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private String code;

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }

        /*public static VersionStatusEnum codeOf(String value){
            //遍历枚举类valuse
            for (VersionStatusEnum versionStatusEnum:values()) {
                // 若枚举类的code值与传值code一致，则返回枚举类的values信息
                if (versionStatusEnum.getValue() == value) {
                    return versionStatusEnum;
                }
            }
            // 若遍历枚举类的valuse，无法匹配code值，则抛出无法匹配异常
            throw new RuntimeException("没有对应枚举！");
        }*/
    }
}
