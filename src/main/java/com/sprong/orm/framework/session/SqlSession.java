package com.sprong.orm.framework.session;

/**
 * 提供一个统一的供外部使用的接口
 * @author: guoqiang.lv
 * @date: 2019/12/20
 * @description: 提供统一的对外部的接口
 */
public interface SqlSession {

    /**
     * 获取Mapper
     * @param clazz Mapper.class
     * @param <T> 泛型 Mapper
     * @return Mapper
     */
    <T> T getMapper(Class<T> clazz);

    /**
     * 查询一条数据
     * @param sql sql语句
     * @param parameter 参数
     * @param <T> sql 映射的dto
     * @return SQL映射的 dto
     */
    <T> T selectOne(String sql, Object parameter);
}
