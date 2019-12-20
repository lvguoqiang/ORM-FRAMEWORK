package com.sprong.orm.dto;

import lombok.Data;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/12
 * @description:
 */
@Data
public class Post {
    private Integer id;
    private Author author;
    private String content;

    private Long authorId;
}
