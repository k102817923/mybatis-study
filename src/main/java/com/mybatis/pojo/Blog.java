package com.mybatis.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Blog")
@Data
public class Blog {
    private String id;
    private String title;
    private String author;
    private Date createTime;
    private int views;
}
