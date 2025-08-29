package com.eduardoinacio.javaspring_socialmedia.repository;

import com.eduardoinacio.javaspring_socialmedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
