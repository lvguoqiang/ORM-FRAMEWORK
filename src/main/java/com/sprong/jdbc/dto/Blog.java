package com.sprong.jdbc.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/12
 * @description:
 */
@Data
public class Blog {
    private Integer id;
    private Author author;
    private List<Post> posts;
}
