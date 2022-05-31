package com.shenzhou.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CIDEntity
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/5/14 14:01
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_cid")
public class CIDEntity {

    @TableId
    private String id;

    private String user_id;

    private String real_name;

    private String cid_number;

    private String cid_front;

    private String cid_reverse;

    private String is_confirm;

    private String is_delete;

    private String create_time;

}
