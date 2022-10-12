package com.example.blog.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("users")
public class Users {
    private int uid;
    private String uemail;
    private String upwd;
    private String uaite;
    private Timestamp utime;
    private int uonline;
}
