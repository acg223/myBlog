package com.example.blog.Bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Reply {
    private int rid;
    private int uid;
    private  int tid;
    private  String rcontent;
    private Timestamp rptime;
    String time;
}
