package com.sprong.orm.framework.binding;

import com.sprong.orm.framework.annoations.Select;
import com.sprong.orm.framework.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Mapper的代理类
 * 通过动态代理来实现
 * 在 invoke 方法中获取到SQL和参数, 然后交由SqlSession去查询
 * @author: guoqiang.lv
 * @date: 2019/12/20
 * @description:
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Objects.nonNull(method.getAnnotation(Select.class))) {
            String sql = getSql(method);
            return sqlSession.selectOne(sql, args[0]);
        }
        return method.invoke(this, args);
    }

    private String getSql(Method method) {
        MapperMethod mapperMethod = new MapperMethod(method);
        return mapperMethod.getSql();
    }
}
