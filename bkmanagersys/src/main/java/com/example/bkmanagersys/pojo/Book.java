package com.example.bkmanagersys.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: bkmanager
 * @description:
 * @author: FENG CHEN
 * @create: 2021-12-01 18:00
 */
@Data
@TableName("borrowliterature")

public class Book {
    @TableId(type = IdType.AUTO)
    private int id;
    private String title;
    private String lendinglibrary;
    private String bill;
    private String subscribe;
    private String lendingcount;
    private String label;
    private String lastuse;
    private String museumuse;
    private String copynumber;
    private String identify;
    private String stadiumstype;
    private String price;
    private String literaturetype1;
    private String literaturetype2;
    private String type;
    private String permanentcollectionlocation;
    private String added;
    private String currentaddress;
    private String piece;
    private String author;
    private String isbn;
    private String booktype;


}
