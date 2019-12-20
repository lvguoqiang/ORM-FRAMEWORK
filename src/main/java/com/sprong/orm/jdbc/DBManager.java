package com.sprong.orm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/12
 * @description:
 */
public class DBManager {

    private String url = "jdbc:mysql://db.hcbm.org:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "hcbm";
    private String password = "hcbm";

    private DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("加载驱动失败");
        }
    }

    public static DBManager newInstance() {
        return InnerClass.dbManager;
    }

    public DBManager(String url, String username, String password) {
        this();
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException("加载连接器失败", e);
        }
    }

    public PreparedStatement getPreparedStatement(String sql) {
        Connection connection = getConnection();
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException("获取statement失败", e);
        }
    }

    public Statement getStatement() {
        try {
            Connection connection = getConnection();
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("获取statement失败", e);
        }
    }

    private static class  InnerClass {
        static DBManager dbManager = new DBManager();
    }
}
