package com.sprong.orm.framework.mapping;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/18
 * @description:
 */
public interface ResultMapping {
    /**
     * 查询一条数据
     * @param resultSet 查询的结果集
     * @param clazz 返回的dto
     * @param <T> 泛型DTO
     * @return sql映射的DTO
     */
    <T> T returnOne(ResultSet resultSet, Class<T> clazz);

    /**
     * 返回多条数据
     * @param resultSet 查询的结果接
     * @param clazz 泛型
     * @param <T> 泛型DTO
     * @return List<T>
     */
    <T> List<T> returnMany(ResultSet resultSet, Class<T> clazz);
}
