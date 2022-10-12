package com.example.blog.Service.Imp;

import com.example.blog.Bean.Reply;
import com.example.blog.Bean.Userinfo;
import com.example.blog.Bean.Users;
import com.example.blog.Mapper.ReplyMapper;
import com.example.blog.Mapper.UserInfoMapper;
import com.example.blog.Mapper.UserMapper;
import com.example.blog.Service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
public class ReplyImp implements ReplyService {
    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public String getReplyByTid(int tid) {
        List<Reply> replies=new ArrayList<>();
        replies=replyMapper.getReplyByTid(tid);
        if (replies!=null) {
            JSONArray js = new JSONArray();
            for (Reply reply : replies) {
                Users users = userMapper.QueryUserById(reply.getUid());
                Userinfo info = userInfoMapper.getInfos(reply.getUid());
                Timestamp rtime = reply.getRptime();
                SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DATE);
                int month = cal.get(Calendar.MONTH) + 1;
                int year = cal.get(Calendar.YEAR);

                String nowyear = year + "-01-01 00:00:00";
                Timestamp yeardate = Timestamp.valueOf(nowyear);

                String nowday = year + "-" + month + "-" + day + " 00:00:00";
                Timestamp date = Timestamp.valueOf(nowday);
                // 此处转换为毫秒数
                long millionSeconds = rtime.getTime();// 毫秒
                long nowSeconds = System.currentTimeMillis();
                long chazhi = nowSeconds - millionSeconds;

                if (chazhi < 60000) {
                    reply.setTime("现在");
                } else if (chazhi < 3600000) {
                    long n = chazhi / 60000;
                    reply.setTime(n + "分钟");
                } else if (rtime.after(date)) {
                    reply.setTime(sdf3.format(rtime));
                } else if (rtime.after(yeardate)) {
                    reply.setTime(sdf.format(rtime));
                } else {
                    reply.setTime(sdf2.format(rtime));
                }
//            js.put(getJsonObj())
                try {
                    js.put(getJsonObj(reply.getRid(), reply.getUid(), reply.getTid(), reply.getRcontent(), reply.getTime(),
                            users.getUaite(), info.getUlogo()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return js.toString();
        }else {
            return null;
        }
    }
    public JSONObject getJsonObj(int rid, int uid, int tid, String rcontent, String time,  String uaite, String ulogo) throws JSONException {
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("rid", rid);
        jsonobj.put("uid", uid);
        jsonobj.put("tid", tid);
        jsonobj.put("rcontent", rcontent);
        jsonobj.put("time", time);
        jsonobj.put("uaite", uaite);
        jsonobj.put("ulogo", ulogo);
        return jsonobj;
    }
}
