package com.workshopmongo.config;

import com.workshopmongo.domain.User;
import com.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

@Autowired
private UserRepository userReposiroty;

    @Override
    public void run(String... arg0) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userReposiroty.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User yanna = new User(null, "Yanna Maria", "yanna@gmail.com");
        User obama = new User(null, "Obama", "obaminha@gmail.com");

        userReposiroty.saveAll(Arrays.asList(maria, yanna, obama));

    }

}
