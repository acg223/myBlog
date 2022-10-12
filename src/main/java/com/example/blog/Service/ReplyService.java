package com.example.blog.Service;

import org.springframework.stereotype.Service;

@Service
public interface ReplyService {
    String getReplyByTid(int tid);
}
