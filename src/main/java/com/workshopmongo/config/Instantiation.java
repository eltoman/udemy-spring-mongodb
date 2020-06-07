package com.workshopmongo.config;

import com.workshopmongo.domain.User;
import com.workshopmongo.repository.UserRepository;
import com.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

@Autowired
private UserRepository userReposiroty;

@Autowired
private UserService userService;

    @Override
    public void run(String... arg0) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        List<User> users = new ArrayList<>();
        users.add(new User(null, "Maria Brown", "maria@gmail.com"));
        users.add(new User(null, "Yanna Maria", "yanna@gmail.com"));
        users.add(new User(null, "Obama", "obaminha@gmail.com"));

        for (User user : users){
            if (!userService.isEmailRegistered(user.getEmail()))
                userReposiroty.save(user);
        }
    }

}
