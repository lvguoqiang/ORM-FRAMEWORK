package com.sprong.orm.framework.executor;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/20
 * @description:
 */
public interface Executor {

    /**
     * 查询一条数据
     * @param sql SQL语句
     * @param parameter 参数
     * @param <T> 返回DTO
     * @return DTO
     */
    <T> T selectOne(String sql, Object parameter);
}
