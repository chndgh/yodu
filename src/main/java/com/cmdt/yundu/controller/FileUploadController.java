package com.cmdt.yundu.controller;


import com.cmdt.yundu.config.StorageProperties;
import com.cmdt.yundu.exception.StorageFileNotFoundException;
import com.cmdt.yundu.service.StorageService;
import com.cmdt.yundu.to.ResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    StorageProperties properties;


    @GetMapping("/")
    public ResponseTO listUploadedFiles() throws IOException {


        List<String> uploadPicPaths = storageService.loadAll().map(path -> {
                    String realPath;
                    realPath = "/" + properties.getLocation() + "/" + path;
                    return realPath;
                }).collect(Collectors.toList());

        return null;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
