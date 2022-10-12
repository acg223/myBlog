package com.example.blog.Controller;

import com.example.blog.Bean.Blog;
import com.example.blog.Bean.Users;
import com.example.blog.Service.BlogService;
import com.example.blog.util.Times;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@Slf4j
@Controller
@ResponseBody
public class BlogControler {
    @Autowired
    BlogService blogService;
    @PostMapping("/addBlog")
    private void addBlog(@RequestParam("tuiwen") String tuiwen,
                         @RequestParam("tpic")MultipartFile tpic,
                         HttpSession session,
                         Model model){
        Users user= (Users) session.getAttribute("user");
        Timestamp btime= Times.getSystemTime();
        Blog blog=new Blog();
        blog.setUid(user.getUid());
        blog.setBcontent(tuiwen);
        blog.setBpic(tpic.getOriginalFilename());
        blog.setBtime(btime);
        int tid=blogService.addBlog(blog);
        System.out.println(blog.getTid());
        log.info(String.valueOf(tid));
        return;
    }
    @PostMapping("/getBlogAll")
    private PageInfo<User> getBlogAll(@RequestParam("pageNum") int pageNum,
                                      @RequestParam("pageSize") int pageSize,
                                      HttpSession session) throws IOException {
        if (pageNum==0||pageSize==0){
            pageNum=1;
            pageSize=4;
        }
        Users users= (Users) session.getAttribute("user");
        PageInfo<User> list=blogService.getBlogAll(pageNum,pageSize,users.getUid());
//        model.addAttribute("Blog",list);
//        JSONArray jsonArray=new JSONArray();
        return list;
    }

    @PostMapping("/updateLike")
    private void updateLike(@RequestParam("method") String method,
                            @RequestParam("tid") int tid,
                            @RequestParam("uid") int uid){
        blogService.updateLike(method, uid, tid);
        return;
    }
}
