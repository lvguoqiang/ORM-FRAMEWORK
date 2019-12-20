package com.sprong.orm.mapper;

import com.sprong.orm.dto.Author;
import com.sprong.orm.framework.annoations.Select;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/20
 * @description:
 */
public interface AuthorMapper {

    /**
     * 根据主键查询
     * @param id 主键
     * @return Author
     */
    @Select("select * from author where id = ?")
    Author selectByPrimaryKey(Integer id);
}
