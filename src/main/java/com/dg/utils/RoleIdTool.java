package com.dg.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色id处理工具
 */
public class RoleIdTool {

    /**
     * 集合字符串转换为int集合
     *
     * @param strs
     * @return
     */
    public static List<Integer> strToIntArray(String strs) {
        List<Integer> list = new ArrayList<>();
        if (strs.length() == 1) {
            list.add(Integer.valueOf(strs));
        } else if (strs.contains(",")) {
            String[] strArray = strs.split(",");
            for (String str : strArray) {
                list.add(Integer.valueOf(str));
            }
        }
        return list;
    }
}
