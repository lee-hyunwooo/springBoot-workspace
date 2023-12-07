package com.kh.springdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.kh.springdb.model")
public class JpaImgUploadListApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaImgUploadListApplication.class, args);
	}

}
