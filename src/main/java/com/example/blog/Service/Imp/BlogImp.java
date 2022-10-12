package com.example.blog.Service.Imp;

import com.example.blog.Bean.Blog;
import com.example.blog.Bean.Userinfo;
import com.example.blog.Bean.Users;
import com.example.blog.Mapper.BlogMapper;
import com.example.blog.Mapper.ReplyMapper;
import com.example.blog.Mapper.UserInfoMapper;
import com.example.blog.Mapper.UserMapper;
import com.example.blog.Service.BlogService;
import com.example.blog.Service.ReplyService;
import com.example.blog.util.Times;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogImp implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    ReplyService replyService;
    @Override
    public int addBlog(Blog blog) {
        blogMapper.addBlog(blog);
        return blog.getTid();

    }
    public PageInfo<User> getBlogAll(int pageNum, int pageSize,int uid1){
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList=blogMapper.getBlogALL();
        for (Blog blog:blogList){
            sumLike(blog.getTid());
            sumforward(blog.getTid());
        }
        List list=new ArrayList();
        for (Blog blog:blogList){
            Users users=new Users();
            Userinfo userinfo=new Userinfo();
            Map map=new HashMap();
            int uid=blog.getUid();
            users=userMapper.QueryUserById(uid);
            userinfo=userInfoMapper.getInfos(uid);
            String reply = replyService.getReplyByTid(blog.getTid());
            map.put("uid",blog.getUid());
            map.put("username",users.getUaite());
            map.put("email",users.getUemail());
            map.put("ulogo",userinfo.getUlogo());
            map.put("tid",blog.getTid());
            map.put("bcontent",blog.getBcontent());
            map.put("btime",blog.getBtime());
            map.put("bpic",blog.getBpic());
            map.put("blike",blog.getBlike());
            map.put("bzhuan",blog.getBtzhuan());
//            if (reply!=null){
//                map.put("breply",reply);
//            }
            if (zhuaned(uid1,blog.getTid())){
                map.put("zhuaned",1);
            }else {
                map.put("zhuaned",0);
            }
            if (liked(uid1,blog.getTid())){
                map.put("liked",1);
            }else {
                map.put("liked",0);
            }
            list.add(map);
        }
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    public void sumLike(int tid) {
        int num=blogMapper.sumlike(tid);
        blogMapper.setLike(num,tid);

    }

    public void sumforward(int tid) {
        int num=blogMapper.sumforward(tid);
        blogMapper.setforward(num,tid);
    }

    public void updateLike(String method, int uid, int tid) {
        if (method.equals("add")){
            Timestamp btime= Times.getSystemTime();
            blogMapper.addLike(uid, tid,btime);
        }else {
            blogMapper.delLike(uid, tid);
        }
    }

    public boolean liked(int uid,int tid){
        boolean flag=false;
        if (blogMapper.liken(uid, tid)>0){
            flag=true;
        }
        return flag;
    }
    public boolean zhuaned(int uid,int tid){
        boolean flag=false;
        if (blogMapper.zhuaned(uid, tid)>0){
            flag=true;
        }
        return flag;
    }
}
