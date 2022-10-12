//package com.example.blog.Service.Imp;
//
//import com.springboot.webboot.Bean.Forwards;
//import com.springboot.webboot.Bean.Likes;
//import com.springboot.webboot.Bean.Utweets;
//import com.springboot.webboot.Mapper.TweetsMapper;
//import com.springboot.webboot.Service.TweetsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class TweetsImp implements TweetsService{
//
//    @Autowired
//    TweetsMapper tweetsMapper;
//
//    @Override
//    public int addTweet(int uid, String tcontent, Timestamp ttime, int tzhuan) {
//       return tweetsMapper.addTweet(uid,tcontent,ttime,tzhuan);
//    }
//
//    @Override
//    public int addPicTweets(int uid, String tcontent, Timestamp ttime, String tpic) {
//        return tweetsMapper.addPicTweet(uid,tcontent,ttime,tpic);
//    }
//
//    @Override
//    public List<Utweets> getTweet(int uid) {
//        return tweetsMapper.getTweet(uid);
//    }
//
//    @Override
//    public boolean selLike(int uid, int tid) {
//        List<Likes> list=tweetsMapper.selLike(uid,tid);
//        if (list.size()>0){
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public int addLike(int uid, int tid, Timestamp ttime) {
//        return tweetsMapper.addLikes(uid,tid,ttime);
//    }
//
//    @Override
//    public int delLikes(int uid, int tid) {
//        return tweetsMapper.delLikes(uid,tid);
//    }
//
//    @Override
//    public Forwards getForward(int tid, int uid) {
//        List<Forwards> list=tweetsMapper.getForward(tid,uid);
//        if (list.size()>0){
//            return list.get(0);
//        }
//        return null;
//    }
//
//    @Override
//    public Forwards getForward(int tid, Timestamp ftime) {
//        List<Forwards> list=tweetsMapper.getForwardByTime(tid,ftime);
//        if (list.size()>0){
//            return list.get(0);
//        }
//        return null;
//    }
//
//    @Override
//    public int addForward(int tid, int stid, int uid, Timestamp ftime) {
//        return 0;
//    }
//
//    @Override
//    public boolean selForward(int uid, int stid) {
//        return false;
//    }
//
//    @Override
//    public Utweets getTweetsByTid(int tid) {
//        List<Utweets> list=tweetsMapper.getTweetsByTid(tid);
//        if (list!=null){
//            return list.get(0);
//        }
//        return null;
//    }
//
//    @Override
//    public List<Utweets> getNewTeets(int uid, List uidList, String nowid) {
//        List<Utweets> list = new ArrayList<Utweets>();
//        if (nowid != null && "".equals(nowid) == false) {
//            int tid=Integer.getInteger(nowid);
//            if (uidList != null) {
//                for (int i = 0; i < uidList.size(); i++) {
//                    list.add(tweetsMapper.getNewTweets(uid,tid));
//                }
//            } else {
//                list.set(0 , tweetsMapper.getNewTweets(uid,tid));
//            }
//            if (list==null){
//                return null;
//            }
//            return list;
//        }
//        return null;
//    }
//
//    @Override
//    public List<Utweets> getTweets(int uid, int page) {
//        List<Utweets> list= new ArrayList<Utweets>();
//        list=tweetsMapper.getTweetsWithPage(uid,page);
//        if (list!=null){
//            return list;
//        }
//        return null;
//    }
//
//    @Override
//    public List<Utweets> getTweets(int uid) {
//        List<Utweets> list= new ArrayList<Utweets>();
//        list=tweetsMapper.getTweets(uid);
//        if (list!=null){
//            return list;
//        }
//        return null;
//    }
//    public List<Utweets> getAllTweets(int uid,List uidList,int page){
//        List<Utweets> list = new ArrayList<Utweets>();
//            if (uidList != null) {
//                for (int i = 0; i < uidList.size(); i++) {
//                    list.add(tweetsMapper.getTweetsALL(uid,page));
//                }
//            } else {
//                list.set(0 , tweetsMapper.getNewTweets(uid,page));
//            }
//            if (list!=null){
//                return list;
//            }
//            return null;
//    }
//
//}
