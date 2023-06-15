package com.teun.pokemonreadservice.service;

import com.teun.pokemonreadservice.dto.UserPokemonDTO;
import com.teun.pokemonreadservice.models.UserPokemon;
import com.teun.pokemonreadservice.repo.UserPokemonRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPokemonService {

    private static final String FROM_DATABASE = "[ 🌟 ] Retrieved pokemon from database [ 🌟 ]";
    @Autowired
    UserPokemonRepo repo;
    @Autowired
    ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(UserPokemonService.class);
//    @Cacheable(value = "userpokemoncache", key = "#userId")
    public List<UserPokemonDTO> findAllByUserId(Long userId){
        return findAllByUserIdFromDatabase(userId);
    }

    public void deleteUserPokemon(UserPokemonDTO userPokemonDTO){
        deleteUserPokemonFromDataBase(userPokemonDTO);
    }
    public void deleteUserPokemonByUserId(Long userId){
        deleteUserPokemonFromDataBaseByUserId(userId);
    }

    //    @Cacheable(value = "userpokemoncache")
    public List<UserPokemonDTO> findAll(){
        logger.info("found userpokemons");
        return findAllFromDataBase();
    }

//    @CachePut(value = "userPokemon", key ="#userId")
    public UserPokemonDTO saveUserPokemon(UserPokemonDTO userPokemonDTO){
        return saveUserPokemonToDatabase(userPokemonDTO);
    }

    private List<UserPokemonDTO> findAllByUserIdFromDatabase(long userId){
        List<UserPokemonDTO> found = repo.findByUserId(userId);
        logger.info(FROM_DATABASE);
        return found;
    }

    private UserPokemonDTO saveUserPokemonToDatabase(UserPokemonDTO userPokemonDTO){
        UserPokemonDTO savedUserPokemonDTO = repo.save(userPokemonDTO);
        logger.info("[ 🌟 ] Saved userpokemon to database [ 🌟 ]");
        return savedUserPokemonDTO;
    }
    private List<UserPokemonDTO>findAllFromDataBase(){
        List<UserPokemonDTO> found = repo.findAll();
        logger.info(FROM_DATABASE);
        return found;
    }
    private void deleteUserPokemonFromDataBase(UserPokemonDTO userPokemonDTO){
        repo.delete(userPokemonDTO);
    }

    private void deleteUserPokemonFromDataBaseByUserId(Long userId) {
        repo.deleteAllByUserId(userId);
        logger.info("Deleted all userId from db with userId: " + userId);
    }

//    public void updateCache(String cacheKey){
//        Cache cache = cacheManager.getCache("userpokemoncache");
//        if(cache != null){
//            cache.clear();
//            cache.evict(cacheKey);
//            logger.info("[ 🧹 ] Cache Cleared for key: " + cacheKey + " [ 🧹 ]");
//        }
//    }
}
