package com.teun.pokemonreadservice.service;

import com.teun.pokemonreadservice.models.PokemonImg;
import com.teun.pokemonreadservice.repo.PokemonImgRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PokemonImgService {
    private static final String ERROR = "Error: ";
    @Autowired
    PokemonImgRepo repo;

    Logger logger = LoggerFactory.getLogger(PokemonImgService.class);

    public List<PokemonImg> findAllPokemonImgs() {
        return repo.findAll();
    }

    public PokemonImg findPokemonImgById(int id) {

        return repo.findById(id).orElse(null);
    }

    public PokemonImg findByPokemonId(int id) {

        return repo.findByPokemonid(id).orElse(null);
    }
}