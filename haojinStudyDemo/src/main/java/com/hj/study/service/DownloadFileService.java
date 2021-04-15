package com.hj.study.service;

/**
 * @author hutao
 * @className DownloadFileService
 * @description 文件下载
 * @date 2021/4/6 10:26 上午
 */
public interface DownloadFileService {

    /**
     *
     * @param url 请求文件的路径
     * @param filePath 文件保存的路径
     * @param method
     */
    void saveUrlAs(String url, String filePath, String method);
}
