package com.sprong.jdbc.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/18
 * @description:
 */
public class StringUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }
}
