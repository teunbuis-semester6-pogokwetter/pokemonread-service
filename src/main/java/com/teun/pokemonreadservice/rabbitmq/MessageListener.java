package com.teun.pokemonreadservice.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teun.pokemonreadservice.dto.UserPokemonDTO;
import com.teun.pokemonreadservice.service.UserPokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserPokemonService service;
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = "userpokemon_delete_queue")
    public void handleDelete(String message){
        logger.info("[ 🌠 ] " + "Recieved for deletion:"+ message + " [ 🌠 ]");
        UserPokemonDTO userPokemonDTO = null;
        try{
           userPokemonDTO = objectMapper.readValue(message, UserPokemonDTO.class);
        }
        catch (Exception e){
            logger.error("ERROR: " + e);
        }
        if(userPokemonDTO != null){
            service.deleteUserPokemon(userPokemonDTO);
        }
        else{
            logger.info("COULD NOT DELETE USERPOKEMON!");
        }
    }

    @RabbitListener(queues = "user_delete_queue")
    public void handleDeleteUser(String message){
        logger.info("Received user id for deletion");
        try {
            Long userId = objectMapper.readValue(message, Long.class);
            if(userId != null ){
                service.deleteUserPokemonByUserId(userId);

            }
        } catch (JsonProcessingException e) {
            logger.error("ERROR: " + e);
        }
    }
}
