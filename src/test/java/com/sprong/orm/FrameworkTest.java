package com.sprong.orm;

import com.sprong.orm.dto.Author;
import com.sprong.orm.framework.executor.SimpleExecutor;
import com.sprong.orm.framework.session.Configuration;
import com.sprong.orm.framework.session.DefaultSqlSession;
import com.sprong.orm.framework.session.SqlSession;
import com.sprong.orm.mapper.AuthorMapper;
import org.junit.Test;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/20
 * @description:
 */
public class FrameworkTest {

    @Test
    public void selectTest() {
        SqlSession sqlSession = new DefaultSqlSession(new Configuration(), new SimpleExecutor());
        AuthorMapper authorMapper = sqlSession.getMapper(AuthorMapper.class);
        Author author = authorMapper.selectByPrimaryKey(2);
        System.out.println(author);
    }
}
