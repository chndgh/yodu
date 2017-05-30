package com.cmdt.yundu.controller;

import com.cmdt.yundu.model.Message;
import com.cmdt.yundu.model.Photo;
import com.cmdt.yundu.model.User;
import com.cmdt.yundu.repository.UserRepository;
import com.cmdt.yundu.service.IMessageService;
import com.cmdt.yundu.to.NewMessageTO;
import com.cmdt.yundu.to.ResponseTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/**
 * Created by dingguohua on 2017/5/27.
 */
@RestController
@RequestMapping("/message")
@EnableAutoConfiguration
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Resource
    IMessageService messageService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseTO getUser(@PathVariable("id") Long id){
//        ResponseTO rto = new ResponseTO();
        User user = userRepository.findOne(id);
        System.out.println(user);
        return ResponseTO.successResp(user);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseTO createMessage(@RequestBody NewMessageTO message){
        LOGGER.info("Enter MessageController.createMessage, parameter -->" + message.getMessage());
        System.out.println(message.getMessage());
        message.getPhotos().stream().forEach(photo -> System.out.println(photo.getName()));
        ResponseTO verifyResult = verify(message.getMessage());
        if (verifyResult != null){
            return verifyResult;
        }
        message.getMessage().setCreatetime(new Date());
        User user = userRepository.findOne(1l);
        user.setUsername("edward");
        message.getMessage().setUser(user);
        System.out.println(message);
        Message m = messageService.createMessage(message.getMessage());

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
