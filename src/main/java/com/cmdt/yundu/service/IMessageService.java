package com.cmdt.yundu.service;

import com.cmdt.yundu.model.Message;

/**
 * Created by dingguohua on 2017/5/27.
 */
public interface IMessageService {


    //创建新消息
    Message createMessage(Message message);
}
