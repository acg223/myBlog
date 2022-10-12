package com.example.blog.Service;

import com.example.blog.Bean.Blog;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {
    int addBlog(Blog blog);
    PageInfo<User> getBlogAll(int pageNum, int pageSize,int uid);
    void sumLike(int tid);
    void sumforward(int tid);
    void updateLike(String method,int uid,int tid);
}
