package com.lvym.fdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.lvym.fdfs.mapper")
public class BootFdfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootFdfsApplication.class, args);
    }

}
