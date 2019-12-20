package com.sprong.orm;

import com.sprong.orm.dto.Author;
import com.sprong.orm.jdbc.DBManager;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/12
 * @description:
 */
public class JDBCTest {

    @org.junit.Test
    public void test1() throws SQLException {
        DBManager dbManager = DBManager.newInstance();
        PreparedStatement statement = dbManager.getPreparedStatement("select * from author where id = ?");
        statement.setInt(1, 1);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            String id = result.getString(2);
            System.out.println(id);
        }
        statement.close();

    }

    @org.junit.Test
    public void insert() throws SQLException {
        DBManager dbManager = DBManager.newInstance();
        Connection connection = dbManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into author(user_name, password, email) values(?, ?, ?)");
        Author author = new Author();
        author.setUserName("testInsertUserName");
        author.setPassword("testInsertPassword");
        author.setEmail("testInsertEmail");
        statement.setString(1, author.getUserName());
        statement.setString(2, author.getPassword());
        statement.setString(3, author.getEmail());
        statement.execute();
        statement.close();
        connection.close();
    }

    @org.junit.Test
    public void delete() throws SQLException {
        DBManager dbManager = DBManager.newInstance();
        Connection connection = dbManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from author where id = ?");
        statement.setInt(1, 1);
        statement.execute();
        statement.close();
        connection.close();
    }

    @org.junit.Test
    public void update() throws SQLException {
        DBManager dbManager = DBManager.newInstance();
        Connection connection = dbManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("update author set user_name = ?, password = ?, email = ? where id = ?");
        Author author = new Author();
        author.setId(2);
        author.setUserName("testUpdateUserName");
        author.setPassword("testUpdatePassword");
        author.setEmail("testUpdateEmail");
        statement.setString(1, author.getUserName());
        statement.setString(2, author.getPassword());
        statement.setString(3, author.getEmail());
        statement.setLong(4, author.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    @org.junit.Test
    public void queryOne() throws SQLException {
        DBManager dbManager = DBManager.newInstance();
        Connection connection = dbManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("select id, user_name, password, email from author where id = ?");
        statement.setInt(1, 2);
        ResultSet result = statement.executeQuery();
        Author author = new Author();
        if (result.next()) {
            author.setId(result.getInt(1));
            author.setUserName(result.getString(2));
            author.setPassword(result.getString(3));
            author.setEmail(result.getString(4));
        }
        System.out.println(author);
        statement.close();
        connection.close();
    }

    @org.junit.Test
    public void queryMany() throws SQLException {
        DBManager dbManager = DBManager.newInstance();
        Connection connection = dbManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("select id, user_name, password, email from author");
        ResultSet result = statement.executeQuery();
        List<Author> authors = new ArrayList<>();
        while (result.next()) {
            Author author = new Author();
            author.setId(result.getInt(1));
            author.setUserName(result.getString(2));
            author.setPassword(result.getString(3));
            author.setEmail(result.getString(4));
            authors.add(author);
        }
        System.out.println(authors);
        statement.close();
        connection.close();
    }

    @Test
    public void resultSetTest() throws SQLException {
        DBManager dbManager = DBManager.newInstance();
        Connection connection = dbManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("select id, user_name uname, password, email from author where id = ?");
        statement.setInt(1, 2);
        ResultSet result = statement.executeQuery();
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();
        String idName = metaData.getColumnName(1);
        String label = metaData.getColumnLabel(2);
        Assert.assertEquals("uname", label);
        Assert.assertEquals("INT", metaData.getColumnTypeName(1));
        Assert.assertEquals(columnCount, 4);
        Assert.assertEquals("id", idName);
    }
}
