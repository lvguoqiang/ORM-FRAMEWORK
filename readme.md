# ORM框架

## 1. JDBC

> ORM 框架底层使用的还是JDBC, 这里先简要进行下JDBC常用API的介绍和简单封装

### 1.1 jdbc 连接数据库步骤

- 加载驱动
- 获取 Connection
- 获取 Statement 
- 执行SQL & 渲染结果集
- 关闭 Statement & 关闭 Connection

```java
try {
    // 加载驱动
    Class.forName("com.mysql.cj.jdbc.Driver");
    // 获取连接
    Connection connection = DriverManager.getConnection(url, username, password);
    // 获取Statement
    Statement statement = connection.createStatement();
    
    // 执行SQL
    // 拿到SQL的结果集
    ResultSet result = statement.executeQuery("select * from author");
    // 遍历结果集打印
    while (result.next()) {
        String id = result.getString(2);
        System.out.println(id);
    }
    statement.close();
    connection.close();
} catch (ClassNotFoundException e) {
    throw new RuntimeException("加载驱动失败");
}
```

### 1.1.2 常用 API 介绍

- 加载驱动

> 加载驱动就是如下命令, 老版本的驱动包是 com.mysql.jdbc.Driver，新版本是下面这个。

```java
Class.forName("com.mysql.cj.jdbc.Driver")
```

- 获取连接

> 执行SQL时必须先获取数据库连接, 类似于一次会话。获取连接是消耗比较大的一个操作，后续会对这块进行封装。它需要传入三个参数:
>
> - url: 数据库信息（jdbc:mysql://db.hcbm.org:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC）
> - username: 用户名(root)
> - password: 密码(root)

```java
DriverManager.getConnection(url, username, password)
```

- Statement

> 提供执行SQL和获取结果集的方法的接口。我们所有的SQL语句的执行都要通过它来进行。它有两种，一种就是Statement，另一种是 PreparedStatement

- Statement

> 执行的SQL都是已经拼接好的的SQL语句，如 select * from author where id = 1;

```java
Statement statement = connection.createStatement();
ResultSet result = statement.executeQuery("select * from author");
```

- PreparedStatement

> 可以通过占位符方式，进行填充参数

```java
PreparedStatement statement = connection.prepareStatement("select * from author where id = ?");
statement.setInt(1, 1);
ResultSet result = statement.executeQuery();
```

- 建议

> 一般使用的时候都建议使用 preparedStatement, 因为我们很少使用不带查询条件, 不进行拼接的SQL，当然使用Statement也可以通过字符串拼接实现 PreparedStatement。但是我们接受的参数可能是通过前台传过来的，那么很容易早成SQL注入。

### 1.1.3 获取JDBC连接简单封装

> 获取连接和获取Statement是每个SQL都需要的，这里简单封装一下
> 见 com.sprong.orm.jdbc.DBManager

### 1.1.4 ResultSet处理

> 在我们代码中都是通过对象进行处理逻辑的, 而将 ResultSet 转换为我们的dto是一个繁琐的过程, 这里对它进行简单封装。注意这个对于结果集的处理是一个很繁琐的过程，需要考虑很多情况，后面我们会仿写MyBatist源码, 这里我们只是做一个简单封装，只是为了理解框架底层怎么处理

- ResultSetMetaData

> 对 ResultSet 结果集信息的描述

- API

  - getColumnCount： 获取查询列数
  - getColumnLabel：查询列别名
  - getColumnName：查询列名称
  - getColumnTypeName：查询列MySQL类型

  通过这些API我们就可以对结果集进行处理

`com.sprong.orm.framework.mapping.ResultMapping`

# 2. 版本介绍
## 2.1 1.0.0
- JDBC 简单实用
- JDBC 常用 API 介绍
- JDBC 使用的简单封装 
