package com.teun.pokemonreadservice.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teun.pokemonreadservice.dto.UserPokemonDTO;
import com.teun.pokemonreadservice.service.UserPokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
    public class Receiver{

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserPokemonService userPokemonService;
        private CountDownLatch latch = new CountDownLatch(1);

        private Logger logger = LoggerFactory.getLogger(Receiver.class);

        public void receiveMessage(String message){
            logger.info("[ 🌠 ] " + "Recieved from queue:"+ message + " [ 🌠 ]");
            UserPokemonDTO userPokemonDTO = null;
            try{
                userPokemonDTO = objectMapper.readValue(message, UserPokemonDTO.class);
            } catch (JsonMappingException e) {
                logger.error("ERROR: " + e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            if(userPokemonDTO != null){
                userPokemonService.saveUserPokemon(userPokemonDTO);
            }
            latch.countDown();
        }
        public CountDownLatch getLatch(){
            return latch;
        }
}
