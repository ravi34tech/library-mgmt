package com.ravi.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
public class LibraryMgmtApplication {

	public static void main(String[] args) {

		SpringApplication.run(LibraryMgmtApplication.class, args);
		System.out.println("--- Library Management Application Started Successfully ---");
	}

	@GetMapping("/")
	public String test(){
		return "Hello World ..... !!!";
	}

}
