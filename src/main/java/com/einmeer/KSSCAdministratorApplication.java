package com.einmeer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 芊嵛
 * @date 2024/3/5
 */
@SpringBootApplication
@MapperScan("com.einmeer.mapper")
public class KSSCAdministratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(KSSCAdministratorApplication.class);
        System.out.println("已启动");
    }
}
