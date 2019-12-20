package com.sprong.orm.framework.session;

import com.sprong.orm.framework.executor.Executor;

/**
 * SqlSession的默认实现类
 * 里面有两个委托对象 configuration 和 executor
 * 通过这两个委托类实现具体的逻辑
 * @author: guoqiang.lv
 * @date: 2019/12/20
 * @description:
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);
    }

    @Override
    public <T> T selectOne(String sql, Object parameter) {
        return executor.selectOne(sql, parameter);
    }
}
