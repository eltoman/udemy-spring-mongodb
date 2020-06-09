package com.workshopmongo.config;

import com.workshopmongo.domain.Post;
import com.workshopmongo.domain.User;
import com.workshopmongo.dto.AuthorDTO;
import com.workshopmongo.dto.CommentDTO;
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

        userReposiroty.deleteAll();
        postReposiroty.deleteAll();

        List<User> users = new ArrayList<>();
        users.add(new User(null, "Maria Brown", "maria@gmail.com"));
        users.add(new User(null, "Yanna Maria", "yanna@gmail.com"));
        users.add(new User(null, "Obama", "obaminha@gmail.com"));

        //inside joke
        User comentador = comentador();

        for (User user : users){
            if (!userService.isEmailRegistered(user.getEmail())) {
                user = userReposiroty.save(user);
                AuthorDTO author = new AuthorDTO(user);
                Post post1 = new Post(null, sdf.parse("21/03/2018"),  "Partiu viagem " + user.getName() , "Vou viajar para São Paulo. Abraços!", author);
                Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia " + user.getName(), "Acordei feliz hoje!", author);

                CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(comentador));
                CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(comentador));
                CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(comentador));

                post1.getComments().addAll(Arrays.asList(c1, c2));
                post2.getComments().addAll(Arrays.asList(c3));

                postReposiroty.saveAll(Arrays.asList(post1, post2));

                user.getPosts().addAll(Arrays.asList(post1, post2));

                userReposiroty.save(user);
            }
        }


    }

    private User comentador() {
        String emailNeto = "neto_do_timao@gmail.com";
        User comentador;
        if (userService.isEmailRegistered(emailNeto)){
            comentador = userService.findByEmail(emailNeto);
        }else {
            comentador = new User(null, "Craque Neto", emailNeto);
            userReposiroty.save(comentador);
        }
        return comentador;
    }

}
