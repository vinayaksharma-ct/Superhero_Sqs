package com.example.LocalSQS.Repository;

import com.example.LocalSQS.Object.Superhero;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SuperheroRepository extends MongoRepository<Superhero, String> {
    void deleteByName(String name);
//
    Optional<Superhero> findByName(String name);
}
