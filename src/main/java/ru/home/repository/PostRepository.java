package ru.home.repository;

import ru.home.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {

    //    private final Map<Long, Post> list = new HashMap<>();
    private final List<Post> list = new ArrayList<>();
    private final AtomicLong postID = new AtomicLong();

    public List<Post> all() {
        return list;

    }

    public Optional<Post> getById(long id) {
        Post p = null;
        for (Post post : list) {
            if (post.getId() == id) {
                p = post;
                break;
            }
        }
        return Optional.ofNullable(p);
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            postID.incrementAndGet();
            post.setId(postID.get());
            list.add(post);
        } else {
            for (Post post2 : list) {
                if (post2.getId() == post.getId()) {
                    post2.setContent(post.getContent());
                    return post2;
                }
            }
            post.setContent("Post с указанным id не существует");
        }
        return post;
    }

    public void removeById(long id) {
        for (Post post : list) {
            if (post.getId() == id) {
                list.remove(post);
                return;
            }
        }

    }
}
