package com.use;

import org.example.AutoResource;

import java.net.URL;

/**
 * @author zbs
 * @since 2023/7/4
 */
@AutoResource
public class App {
    public static void main(String[] args) {
        // 执行 AnnotationProcess，自动生成了tmp.cnf文件
        URL resource = App.class.getClassLoader().getResource("tmp.cnf");
        System.out.println(resource);
    }
}
