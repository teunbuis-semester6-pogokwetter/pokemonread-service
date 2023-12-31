package com.teun.pokemonreadservice.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class MQConfig {
    static final String QUEUENAME = "userpokemon_queue";

    static final String DELETEQUEUENAME = "userpokemon_delete_queue";
    static final String EXCHANGENAME = "userpokemon_exchange";
    static final String ROUTINGKEY = "userpokemon_routingkey";

    static final String DELETEROUTINGKEY = "userpokemon_delete_routingkey";

    static final String QUEUE_USER_DELETE = "user_delete_queue";

    static final String EXCHANGE_USER_DELETE = "user_exchange_delete";

    static final String ROUTINGKEY_USER_DELETE = "user_delete_routingkey";

    @Bean
    Queue userDeleteQueue(){
        return new Queue(QUEUE_USER_DELETE, true);
    }
    @Bean
    TopicExchange userDeleteExchange(){
        return new TopicExchange(EXCHANGE_USER_DELETE);
    }
    @Bean
    Binding bindingUserDelete(){
        return BindingBuilder.bind(userDeleteQueue()).to(userDeleteExchange()).with(ROUTINGKEY_USER_DELETE);
    }

    @Bean
    Queue userPokemonQueue(){
        return new Queue(QUEUENAME, true);
    }

    @Bean
    Queue userPokemonDeleteQueue(){
        return new Queue(DELETEQUEUENAME, true);
    }

    @Bean
    TopicExchange userPokemonExchange(){
        return new TopicExchange(EXCHANGENAME);
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(userPokemonQueue()).to(userPokemonExchange()).with(ROUTINGKEY);
    }
    @Bean
    Binding bindingDelete(){
        return BindingBuilder.bind(userPokemonDeleteQueue()).to(userPokemonExchange()).with(DELETEROUTINGKEY);
    }
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUENAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
