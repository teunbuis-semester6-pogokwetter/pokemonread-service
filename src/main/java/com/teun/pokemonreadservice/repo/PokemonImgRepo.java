package com.teun.pokemonreadservice.repo;

import com.teun.pokemonreadservice.models.PokemonImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PokemonImgRepo extends JpaRepository<PokemonImg, Integer> {
    Optional<PokemonImg> findById(int id);
    Optional<PokemonImg> findByPokemonid(int id);
}
