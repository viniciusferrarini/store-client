package br.com.slotshop.storeclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "br.com.slotshop.*")
@ComponentScan("br.com.slotshop.*")
@EnableJpaRepositories("br.com.slotshop.*")
public class StoreClientApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StoreClientApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(StoreClientApplication.class, args);
	}
}
