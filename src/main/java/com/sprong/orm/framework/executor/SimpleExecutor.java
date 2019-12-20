package com.sprong.orm.framework.executor;

import com.sprong.orm.dto.Author;
import com.sprong.orm.framework.mapping.ResultMapping;
import com.sprong.orm.framework.mapping.ResultMappingImpl;
import com.sprong.orm.jdbc.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/20
 * @description:
 */
public class SimpleExecutor implements Executor {

    @Override
    public <T> T selectOne(String sql, Object parameter) {
        DBManager dbManager = DBManager.newInstance();
        Connection connection = dbManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (Integer) parameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultMapping resultMapping = new ResultMappingImpl();
            return (T) resultMapping.returnOne(resultSet, Author.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
