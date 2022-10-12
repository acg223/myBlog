package com.example.blog;

import com.example.blog.Bean.Blog;
import com.example.blog.Controller.BlogControler;
import com.example.blog.Service.BlogService;
import com.example.blog.Service.Imp.BlogImp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Slf4j
@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private BlogService blogService;
    @Test
    void contextLoads() {

    }

}
