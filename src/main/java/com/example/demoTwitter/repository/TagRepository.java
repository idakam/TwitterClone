package com.example.demoTwitter.repository;

import com.example.demoTwitter.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{
    Tag findByPhrase(String phrase);
}
