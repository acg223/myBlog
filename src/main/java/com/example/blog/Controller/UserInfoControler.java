package com.example.blog.Controller;

import com.example.blog.Bean.Users;
import com.example.blog.Service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Controller
public class UserInfoControler {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/updateBrithday")
    private void updateBrithday(@RequestParam("date") String date,
                                @RequestParam("uid") int uid,
                                HttpServletResponse response) throws IOException {
        String brithy = date.substring(0, date.indexOf("日")).replaceAll("[\u4e00-\u9fa5]", "-") + " 00:00:00";
        Timestamp brithyDate = Timestamp.valueOf(brithy);
        userInfoService.updateBirthday(brithyDate, uid);
        log.info("date"+date);
        log.info("uid"+uid);
        response.getWriter().write("ok");
    }
    @ResponseBody
    @PostMapping("/updateUlogo")
    private void updateUlogo(@RequestParam("file") MultipartFile file,
                             HttpServletResponse response,
                             HttpSession session) throws IOException {
System.out.println(file.getOriginalFilename());
        Users users= (Users) session.getAttribute("user");
        String filePath=("src/main/resources/static/images/")+users.getUemail();
        String uuidFileName = UUID.randomUUID().toString();
        filePath = filePath  +"/"+ uuidFileName;
        File realPath = new File(filePath);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        //上传文件地址
        System.out.println("上传文件保存地址：" + realPath);
        System.out.println(realPath);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
//        file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath + "/" + file.getOriginalFilename()));
        userInfoService.updateUlogo(uuidFileName + "/" + file.getOriginalFilename(),users.getUid());
        response.getWriter().write("ok");
    }
    @PostMapping("/updateAbout")
    private void updateAbout(@RequestParam("about") String about,
                             @RequestParam("uid") int uid,
                             HttpServletResponse response) throws IOException {
        userInfoService.updateAbout(about,uid);
        response.getWriter().write("ok");
    }
}
