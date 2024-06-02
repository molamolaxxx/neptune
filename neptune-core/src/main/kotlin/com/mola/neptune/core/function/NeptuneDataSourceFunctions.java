package com.mola.neptune.core.function;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : molamola
 * @Project: neptune
 * @Description:
 * @date : 2024-06-02 12:53
 **/
public class NeptuneDataSourceFunctions {

    /**
     * 从mysql获取数据
     * @param db
     * @param tb
     * @param where
     * @return
     */
    public static List<String> fetchFromMysql(String db, String tb, List<String> where) {
        return new ArrayList<>();
    }

    /**
     * 从redis获取数据
     * @param db
     * @param where
     * @return
     */
    public static List<String> fetchFromRedis(String db, List<String> where) {
        // 通过db路由连接
        // where中的值是key
        return new ArrayList<>();
    }
}
