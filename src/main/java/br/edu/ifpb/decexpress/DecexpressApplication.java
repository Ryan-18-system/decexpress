package br.edu.ifpb.decexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DecexpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecexpressApplication.class, args);
	}

}
