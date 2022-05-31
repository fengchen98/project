package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ProblemEntity
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/24 14:25
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_problem")
public class ProblemEntity {

    @TableId
    private String id;

    private String username;

    private String type;

    private String title;

    private String content;

    private String html;

    private String status;

    private String is_delete;

    private String create_time;

}
