package ru.home.repository;

import ru.home.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {

    //    private final Map<Long, Post> list = new HashMap<>();
    private final Map<Long, Post> list = new ConcurrentHashMap<>();
    private final AtomicLong postID = new AtomicLong();

    public List<Post> all() {
        return new ArrayList<>(list.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(list.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            postID.incrementAndGet();
            post.setId(postID.get());
            list.put(post.getId(), post);
            return post;
        } else {
            if (list.containsKey(post.getId())) {
                list.put(post.getId(), post);
                return post;
            }

            post.setContent("Post с указанным id не существует");
        }
        return post;
    }

    public void removeById(long id) {
        list.remove(id);

    }
}
