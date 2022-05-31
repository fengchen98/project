package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ConcatEntity
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/5/13 19:42
 * @Version 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_concat")
public class ConcatEntity {

    @TableId
    private String id;

    private String username;

    private String type;

    private String name;

    private String tel;

    private String email;

    private String subject;

    private String content;

    private String is_delete;

    private String status;

    private String create_time;
}
