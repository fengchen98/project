package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName NewsEntity
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/25 16:36
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "t_news")
public class NewsEntity {

    @TableId
    private String id;

    private String username;

    private String image;

    private String type;

    private String title;

    private String description;

    private String content;

    private String html;

    private String status;

    private String is_delete;

    private String create_time;

}
