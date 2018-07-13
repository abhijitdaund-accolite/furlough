package com.accolite.furlough;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accolite.furlough.service.FileStorageService;


@SpringBootApplication
public class BackendApplication implements CommandLineRunner{
	@Resource
	FileStorageService filestorageService;
    public static void main(final String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
    @Override
	public void run(String... arg) throws Exception {
		filestorageService.deleteAll();
		filestorageService.init();
	}
}
