package com.workshopmongo.config;

import com.workshopmongo.domain.Post;
import com.workshopmongo.domain.User;
import com.workshopmongo.dto.AuthorDTO;
import com.workshopmongo.repository.PostRepository;
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
private PostRepository postReposiroty;

@Autowired
private UserService userService;

    @Override
    public void run(String... arg0) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        //userReposiroty.deleteAll();
        //postReposiroty.deleteAll();

        List<User> users = new ArrayList<>();
        users.add(new User(null, "Maria Brown", "maria@gmail.com"));
        users.add(new User(null, "Yanna Maria", "yanna@gmail.com"));
        users.add(new User(null, "Obama", "obaminha@gmail.com"));

        for (User user : users){
            if (!userService.isEmailRegistered(user.getEmail())) {
                user = userReposiroty.save(user);
                AuthorDTO author = new AuthorDTO(user);
                Post post1 = new Post(null, sdf.parse("21/03/2018"),  "Partiu viagem " + user.getName() , "Vou viajar para São Paulo. Abraços!", author);
                Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia " + user.getName(), "Acordei feliz hoje!", author);

                postReposiroty.saveAll(Arrays.asList(post1, post2));
            }
        }


    }

}
