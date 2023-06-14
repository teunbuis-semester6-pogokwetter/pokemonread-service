package com.teun.pokemonreadservice.repo;

import com.teun.pokemonreadservice.dto.UserPokemonDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPokemonRepo extends MongoRepository<UserPokemonDTO, String> {
    List<UserPokemonDTO> findByUserId(Long userId);
    List<UserPokemonDTO> findByNickName(String nickName);

    void deleteAllByUserId(Long userId);
}
