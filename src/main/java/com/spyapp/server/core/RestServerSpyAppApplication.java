package com.spyapp.server.core;

import com.spyapp.server.core.beans.entities.Stalker;
import com.spyapp.server.core.beans.repositories.StalkerRepository;
import com.spyapp.server.util.RandomStringGenerator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class RestServerSpyAppApplication {

    @Autowired
    StalkerRepository stalkerRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestServerSpyAppApplication.class, args);
    }

    @Bean
    InitializingBean initRows(){
        return () -> {
            stalkerRepository.save(new Stalker("albert","password","email.email@email.com",new Date(),new Date()));
            stalkerRepository.save(new Stalker("tomeczek","wodeczeka","email.email@email.com",new Date(),new Date()));

            for(int i = 0;i<10;i++){
                stalkerRepository.save(new Stalker(RandomStringGenerator.getInstance().getString(10),RandomStringGenerator.getInstance().getString(10),"email.email@email.com",new Date(),new Date()));
            }
        };
    }
}
