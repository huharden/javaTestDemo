package com.hj.study.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

@Slf4j
public class FileScreenTest {

    //java8之前的文件筛选法 必须要这样
    @Test
    public void fileScreenTest1() {
        File[] hiddenFiles2 = new File(".").listFiles(new FileFilter() {
            public boolean accept(File file) {
                boolean hidden = file.isHidden();
                log.info("是否隐藏：" + hidden);
                return hidden;
            }
        });

    }

    //java8风格的文件筛选 简洁
    @Test
    public void fileScreenTest2() {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        //这种怎么向上面一样打印出来 ？
    }

}
