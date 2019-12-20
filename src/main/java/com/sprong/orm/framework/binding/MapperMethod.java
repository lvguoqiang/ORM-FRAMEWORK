package com.sprong.orm.framework.binding;

import com.sprong.orm.framework.annoations.Select;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/20
 * @description:
 */
public class MapperMethod {
    private String sql;
    private Object returnType;

    public MapperMethod(Method method) {
        Select annotation = null;
        if (Objects.nonNull(annotation  = method.getAnnotation(Select.class))) {
            sql = annotation.value()[0];
        }
        returnType = method.getReturnType();
    }

    public String getSql() {
        return sql;
    }

    public Object getReturnType() {
        return returnType;
    }
}
