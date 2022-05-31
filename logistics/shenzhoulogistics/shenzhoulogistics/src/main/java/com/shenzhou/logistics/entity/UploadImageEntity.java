package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UploadImageEntity
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/11 15:38
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("image")
public class UploadImageEntity {

    @TableId
    private String id;

    private String userid;

    private String apply;

    private String originname;

    private String newname;

    private String suffix;

    private String path;

    private String size;

    private String filetype;

    private String isimg;

    private Integer loadcounts;

    private Integer create_time;

}
