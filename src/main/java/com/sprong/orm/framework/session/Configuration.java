package com.sprong.orm.framework.session;

import com.sprong.orm.framework.binding.MapperProxy;

import java.lang.reflect.Proxy;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/20
 * @description:
 */
public class Configuration {

    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz},
            new MapperProxy(sqlSession));
    }
}
