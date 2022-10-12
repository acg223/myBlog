package com.example.blog.Controller;


import com.example.blog.Bean.Userinfo;
import com.example.blog.Bean.Users;
import com.example.blog.Service.UserInfoService;
import com.example.blog.Service.UserService;
import com.example.blog.util.Times;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;

@Slf4j
@Controller
public class UserController {

    @GetMapping(value = {"/","/login"})
    private String loginPage(){
        return "login";
    }
    @GetMapping( value = {"/toregister"})
    private String registerPage(){return "register";}
    @GetMapping(value = {"/index"})
    private String indexPage(){return "twitter";}

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @PostMapping("/CheckLogin")
    private String CheckLogin(HttpSession session,
                             @RequestParam String uemail,
                             @RequestParam String password,
                             Model model,
                             HttpServletRequest request){
        Users users= userService.CheckLogin(uemail,password);
        if (users!=null) {
            //存储登录用户
            Userinfo userinfo=userInfoService.getInfos(users.getUid());
            ServletContext application=request.getSession().getServletContext();
            HttpSession oldSession= (HttpSession) application.getAttribute(users.getUemail());
            if (oldSession!=null){
                oldSession.removeAttribute("user");
            }
            session.setAttribute("user",users);
//            return "redirect:/main.html";
            session.setAttribute("info",userinfo);
            application.setAttribute(users.getUemail(),session);
            Integer onlineNum=(Integer) application.getAttribute("onlineNum");
            if (onlineNum==null){
                onlineNum=1;
            }else {
                onlineNum=onlineNum+1;
            }
            application.setAttribute("onlineNum", onlineNum);
            return updateOnline(session);
        }
        if(users==null){
            model.addAttribute("display","show");
            return "login";
        }

        return "login";
    }
    @GetMapping("/main.html")
    private String mainPage(HttpSession session,
                           Model model){
        Object loginUser=session.getAttribute("user");
        Users users= (Users) session.getAttribute("user");
        Userinfo userinfo= (Userinfo) session.getAttribute("info");
        System.out.println(userinfo);

        if (loginUser!=null){
            model.addAttribute("user",users);
            model.addAttribute("info",userinfo);
            return "main";
        }
        else {
            model.addAttribute("msg","false");
            return "login";
        }
    }
    @GetMapping("/findUserById")
    private Users findUserById(int id,Model model){
        Users users=userService.QueryUserById(id);
        model.addAttribute("User",users);
        return users;
    }
    @GetMapping("/CheckUser")
    private void CheckUser(@RequestParam("username") String username,
                          Model model,
                          HttpServletResponse response) throws IOException {
        Users users= userService.CheckUserName(username);
        if (users!=null){
            response.getWriter().println("no");
        }else {
            response.getWriter().println("yes");
        }
    }
    @PostMapping("/Register")
    private String Register(@RequestParam("name") String name,
                           @RequestParam("uname") String uname,
                           @RequestParam("pwd") String pwd,
                           @RequestParam("order") String order,
                           HttpSession session) {
        Users users = new Users();
        users.setUemail(uname);
        users.setUaite(name);
        users.setUpwd(pwd);
        if (order.equals("first")){
            return "register";
        }else {
            session.setAttribute("uname",users.getUemail());
            session.setAttribute("name",users.getUaite());
            session.setAttribute("pwd",users.getUpwd());
//            return "next";
            return "redirect:next.html";
        }
    }
    @GetMapping("next.html")
    private String toNext(){
//
         return "next";
    }

    @GetMapping("/signIn")
    private String signIn(HttpServletRequest request,
                         HttpSession session, Model model,
                         @RequestParam("ycode") String ycode) throws IOException {
        System.out.println(ycode);
        Users users=new Users();
        users.setUaite((String) session.getAttribute("name"));
        users.setUemail((String) session.getAttribute("uname"));
        users.setUpwd((String) session.getAttribute("pwd"));
//        users.setUaite(aite);
        Timestamp utime= Times.getSystemTime();
        users.setUtime(utime);
        if (ycode.equals("1234")){
            userService.addUser(users);
            Users users1=userService.CheckUserName(users.getUemail());
            if (users1!=null){
                ServletContext application=session.getServletContext();
                Integer zhuceNum= (Integer) application.getAttribute("zhunceNum");
                if (zhuceNum==null){
                    zhuceNum=1;
                }else {
                    zhuceNum+=1;
                }
                application.setAttribute("newTweetNum",zhuceNum);
                userService.CheckLogin(users1.getUemail(),users1.getUpwd());
                int m=userInfoService.addUserinfo(users1.getUid());
                if (m>0){
                    Userinfo userinfo=userInfoService.getInfos(users1.getUid());
//                String folder=("E:\\appOrder\\test\\images\\"+ users1.getUname());
//                String folder=request.getServletContext().getRealPath("/img/")+users.getUaite();
//                String img=request.getServletContext().getRealPath("/img");
//                log.info(img);
                    String folder=("/images/")+users.getUemail();
                    log.info(folder);
                    String img=("src/main/resources/static/img");
//                String img=("/img");
                    File file=new File(folder);
                    if (!file.exists()){
                        file.mkdir();
                    }
                    InputStream is=new FileInputStream(img+"/"+userinfo.getUlogo());
                    OutputStream os=new FileOutputStream(folder+"/"+userinfo.getUlogo(),true);
                    byte[] b=new byte[1024];
                    int a=is.read(b);
                    while (a>0){
                        os.write(b,0,a);
                        a=is.read(b);
                    }
                    os.close();
                    is.close();
                    session.setAttribute("user",users1);
                    session.setAttribute("info",userinfo);
                    return "start";
                }
            }
        }
        return "/";
    }

    @GetMapping("/CheckAite")
    private void CheckAite(@RequestParam("name") String name,
                          HttpServletResponse response) throws IOException {
        boolean success=userService.CheckUaite(name);
//        if (ycode.equals("1234")){
//            success=false;
//        }
        if (success){
            response.getWriter().println("no");
        }else {
            response.getWriter().println("yes");
        }

    }
    @GetMapping("/CheckYcode")
    private void CheckYcode(@RequestParam("ycode") String ycode,
                           HttpServletResponse response) throws IOException {
        boolean success=true;
//                userService.CheckUaite(name);
        if (ycode.equals("1234")){
            success=false;
        }
        if (success){
            response.getWriter().println("no");
        }else {
            response.getWriter().println("yes");
        }

    }
    @GetMapping("/Online")
    private String updateOnline(HttpSession session){
        Users users=(Users)session.getAttribute("user");
        userService.updateOnline(1,users.getUid());
        Users users1=userService.QueryUserById(users.getUid());
        session.setAttribute("user",users1);
                System.out.println(users);
        return "redirect:/main.html";
    }
    @GetMapping("/exit")
    private String Quit(HttpSession session,HttpServletRequest request){
        if (session.getAttribute("user")==null){
            session.invalidate();
            return "login";
        }
        Users users= (Users) session.getAttribute("user");
        ServletContext application=request.getSession().getServletContext();
        application.removeAttribute(((Users) session.getAttribute("user")).getUaite());
        Integer onlineNum= (Integer) application.getAttribute("onlineNum");
        if (onlineNum>0){
            application.setAttribute("onlineNum",onlineNum-1);
        }
        userService.updateOnline(0,users.getUid());
        return "login";
    }

}
