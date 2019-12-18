package com.sprong.jdbc.dto;

import lombok.Data;

/**
 * @author: guoqiang.lv
 * @date: 2019/12/12
 * @description:
 */
@Data
public class Author {
    private Integer id;
    private String userName;
    private String password;
    private String email;
}
