package com.sprong.orm;

import com.sprong.orm.dto.Author;
import com.sprong.orm.framework.mapping.ResultMapping;
import com.sprong.orm.framework.mapping.ResultMappingImpl;
import com.sprong.orm.jdbc.DBManager;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/18
 * @description:
 */
public class ResultMappingTest {

    private ResultMapping resultMapping = new ResultMappingImpl();

    @Test
    public void returnOneTest() throws SQLException {
        DBManager dbManager = DBManager.newInstance();
        Connection connection = dbManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("select id, user_name, password, email from author where id = ?");
        statement.setInt(1, 2);
        ResultSet result = statement.executeQuery();

        Author author = resultMapping.returnOne(result, Author.class);
        System.out.println(author);
    }

    @Test
    public void returnManyTest() throws SQLException {
        DBManager dbManager = DBManager.newInstance();
        Connection connection = dbManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("select id, user_name, password, email from author");
        ResultSet result = statement.executeQuery();
        List<Author> authors = resultMapping.returnMany(result, Author.class);
        System.out.println(authors);
    }
}
