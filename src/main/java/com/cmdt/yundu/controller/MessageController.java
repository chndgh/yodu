package com.cmdt.yundu.controller;

import com.cmdt.yundu.model.Message;
import com.cmdt.yundu.service.IMessageService;
import com.cmdt.yundu.to.ResponseTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by dingguohua on 2017/5/27.
 */
@Controller
@RequestMapping("/message")
@EnableAutoConfiguration
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Resource
    IMessageService messageService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseTO createMessage(@RequestBody Message message){
        LOGGER.info("Enter MessageController.createMessage, parameter -->" + message);

        ResponseTO verifyResult = verify(message);
        if (verifyResult != null){
            return verifyResult;
        }

        Message m = messageService.createMessage(message);

        if (m != null){
            return ResponseTO.successResp();
        } else {
            return ResponseTO.failResp("发送消息失败");
        }

    }


    private ResponseTO verify(Message message) {
        if (message == null){
            return ResponseTO.failResp("empty param!");
        }
        return null;
    }
}
