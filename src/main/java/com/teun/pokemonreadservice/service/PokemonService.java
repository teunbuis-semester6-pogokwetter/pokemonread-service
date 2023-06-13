package com.teun.pokemonreadservice.service;

import com.teun.pokemonreadservice.models.Pokemon;
import com.teun.pokemonreadservice.repo.PokemonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private static final String FROM_DATABASE = "[ 🌟 ] Retrieved pokemon from database [ 🌟 ]";
    @Autowired
    PokemonRepo repo;
    Logger logger = LoggerFactory.getLogger(PokemonService.class);
    public Pokemon findPokemonByDexNumber(long dexNumber){
        return findByPokemonDexNumberFromDatabase(dexNumber);
    }
    public Pokemon findPokemonByName(String name){
        return findByNameFromDataBase(name);
    }
    public List<Pokemon> findAllPokemon(){
        return findAllFromDataBase();
    }
    private List<Pokemon> findAllFromDataBase(){
        logger.info(FROM_DATABASE);
        return repo.findAll();
    }
    private Pokemon findByNameFromDataBase(String name){
        logger.info(FROM_DATABASE);
        return repo.findByName(name).orElse(null);
    }
    private Pokemon findByPokemonDexNumberFromDatabase(long dexNumber){
        logger.info(FROM_DATABASE);
        return repo.findByDexNumber(dexNumber).orElse(null);
    }
}
