package com.cmdt.yundu.service.Impl;

import com.cmdt.yundu.model.Message;
import com.cmdt.yundu.repository.MessageRepository;
import com.cmdt.yundu.service.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dingguohua on 2017/5/27.
 */
@Service
public class MessageServiceImpl implements IMessageService{

    @Autowired
    MessageRepository messageRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    /**
     * 创建新消息
     */
    public Message createMessage(Message message){
        LOGGER.info("Enter MessageServiceImpl.createMessage, parameters Message -->" + message.toString());
        return messageRepository.save(message);
    }
}
