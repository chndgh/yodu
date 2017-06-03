package com.cmdt.yundu.controller;


import com.cmdt.yundu.model.Message;
import com.cmdt.yundu.model.Photo;
import com.cmdt.yundu.model.User;
import com.cmdt.yundu.repository.PhotoRepository;
import com.cmdt.yundu.repository.UserRepository;
import com.cmdt.yundu.service.IMessageService;
import com.cmdt.yundu.to.ResponseTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

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

    @Autowired
    PhotoRepository photoRepository;



    @RequestMapping(value = "/postNewMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseTO postNewMessage(HttpServletRequest request){
        LOGGER.info("Enter MessageController.postNewMessage, parameter -->");
        //mock user
        User user = userRepository.findOne(1l);
        user.setUsername("edward");
        //create new Message
        Message message = new Message();
        message.setCreatetime(new Date());
        String content = request.getParameter("content");
        message.setContent(content);
        message.setUser(user);
        //save photo
         return  savePhoto(request,message,user);
    }

    private ResponseTO savePhoto(HttpServletRequest request,Message message,User user){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        System.out.println("enter savePhoto");
        if (multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator iterator = multipartRequest.getFileNames();
            if (iterator == null){
                return ResponseTO.failResp("empty param!");
            }
            while(iterator.hasNext()){
                String name = (String)iterator.next();
                System.out.println("name" + name);
                MultipartFile file = multipartRequest.getFile(name);
                if (file != null){
                    String fileName=file.getOriginalFilename();
                    String path = "C:/work/projects/yodu/upload/" + fileName;
                    File localFile = new File(path);
                    if (!localFile.getParentFile().exists()){
                        localFile.getParentFile().mkdirs();
                    }
                    try {
                        file.transferTo(localFile);
                        Photo photo = new Photo();
                        photo.setUser(user);
                        photo.setMessage(message);
                        photo.setAddress(path);
                        photo.setName(fileName);
                        photoRepository.save(photo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return ResponseTO.successResp();
        }
        return ResponseTO.failResp("file content not right!");
    }
}
