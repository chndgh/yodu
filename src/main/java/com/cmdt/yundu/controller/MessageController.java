package com.cmdt.yundu.controller;

import com.cmdt.yundu.config.StorageProperties;
import com.cmdt.yundu.exception.StorageException;
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
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;
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

//    @Autowired
//    StorageProperties storageProperties;

    private final Path rootLocation;

    @Autowired
    public MessageController(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

//    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseTO getUser(@PathVariable("id") Long id){
////        ResponseTO rto = new ResponseTO();
//        User user = userRepository.findOne(id);
//        return ResponseTO.successResp(user);
//    }

    @RequestMapping(value = "/savePhoto", method = RequestMethod.POST)
    @ResponseBody
    public void savePhotos(HttpServletRequest request){
        LOGGER.info("enter MessageController.savePhotos");
        int id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        System.out.println("content = " +content);
        System.out.println("id=="+id);
        System.out.println("request.getSession() : " + request.getSession());
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator iterator = multipartRequest.getFileNames();
            while(iterator.hasNext()){
                String name = (String)iterator.next();
                System.out.println("name:" + name);
                System.out.println("context path: " + ClassUtils.getDefaultClassLoader().getResource("").getPath());
                MultipartFile file = multipartRequest.getFile(name);
                if (file != null){
                    String fileName=file.getOriginalFilename();
                    String path = "C:/work/projects/yodu/upload/" + fileName;
                    File localFile = new File(path);
                    if (!localFile.getParentFile().exists()){
                        localFile.getParentFile().mkdirs();
                        System.out.println("parent: " + localFile.getParentFile().getPath());
                    }

                    try {
                        file.transferTo(localFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }




    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseTO createMessage(@RequestBody NewMessageTO message){
        LOGGER.info("Enter MessageController.createMessage, parameter -->" + message.getMessage());
        message.getPhotos().stream().forEach(photo -> System.out.println(photo.getName()));
        ResponseTO verifyResult = verify(message.getMessage());
        if (verifyResult != null){
            return verifyResult;
        }

        message.getMessage().setCreatetime(new Date());

        //mock user
        User user = userRepository.findOne(1l);
        user.setUsername("edward");
        message.getMessage().setUser(user);

        //store pictures
//        message.getImages().stream().forEach(image ->{
//            if (image.isEmpty()) {
//                throw new StorageException("Failed to store empty file " + image.getName());
//            }
//
//            try {
//                Files.copy(image.getInputStream(), rootLocation.resolve(image.getOriginalFilename()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
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
