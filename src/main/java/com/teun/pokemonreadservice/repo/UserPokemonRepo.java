package com.teun.pokemonreadservice.repo;

import com.teun.pokemonreadservice.dto.UserPokemonDTO;
import com.teun.pokemonreadservice.models.UserPokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPokemonRepo extends MongoRepository<UserPokemonDTO, String> {
    List<UserPokemon> findByUserId(Long userId);
    List<UserPokemon> findByNickName(String nickName);
}
