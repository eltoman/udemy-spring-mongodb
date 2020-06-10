package com.workshopmongo.services;

import com.workshopmongo.domain.Post;

import com.workshopmongo.repository.PostRepository;
import com.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return postRepository.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> findInBodyText(String text) {
        return postRepository.findInBodyText(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        minDate = new Date(minDate.getTime());
        maxDate = new Date(maxDate.getTime() + 24  * 60 * 60 * 1000);
        return postRepository.fullSearch(text, minDate, maxDate);
    }

}
