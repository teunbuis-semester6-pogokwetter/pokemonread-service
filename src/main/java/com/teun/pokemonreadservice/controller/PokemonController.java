package com.teun.pokemonreadservice.controller;

import com.teun.pokemonreadservice.dto.PokemonDTO;
import com.teun.pokemonreadservice.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    PokemonService service;

    Logger logger = LoggerFactory.getLogger(PokemonController.class);
    @GetMapping("/dex/{dexNumber}")
    public ResponseEntity<PokemonDTO> getPokemonById(@PathVariable(value = "dexNumber")Long dexNumber){
        try{
            PokemonDTO pokemon = service.findPokemonByDexNumber(dexNumber);
            if(pokemon != null){
                return ResponseEntity.ok().body(pokemon);
            }
            else {
                return ResponseEntity.noContent().build();
            }
        }
        catch (Exception e){
            logger.error("Error: " + e);
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping()
    public ResponseEntity<List<PokemonDTO>> getAllPokemon(){
        try{
            List<PokemonDTO> pokemons = service.findAllPokemon();
            if(pokemons != null){
                return ResponseEntity.ok().body(pokemons);

            }
            else {
                return ResponseEntity.noContent().build();
            }
        }
        catch (Exception e){
            logger.error("Error: " + e);
            return ResponseEntity.badRequest().build();
        }
    }
}
