package com.teun.pokemonreadservice.repo;

import com.teun.pokemonreadservice.dto.PokemonDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PokemonRepo extends MongoRepository<PokemonDTO, String> {
    Optional<PokemonDTO> findByDexNumber(Long dexNumber);
    Optional<PokemonDTO> findByName(String name);
}
