package com.hj.study.controller;

import com.hj.study.service.DownloadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hutao
 * @className DoenloadFileController
 * @description TODO
 * @date 2021/4/6 1:37 下午
 */
@RestController
@RequestMapping("/downloadFileController")
public class DownloadFileController {

    @Autowired
    private DownloadFileService downloadFileService;

    @GetMapping("/downloadAs")
    public void downloadAs() {
        String photoUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        String fileName = photoUrl.substring(photoUrl.lastIndexOf("/"));
        //System.out.println("fileName---->"+fileName);
        String filePath = "/Users/hutao/Downloads/";
        downloadFileService.saveUrlAs(photoUrl, filePath + fileName, "GET");
        System.out.println("Run ok!/n<BR>Get URL file ");
    }
}
