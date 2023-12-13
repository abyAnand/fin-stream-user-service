package com.finStream.userservice.broker.consumer;

import com.finStream.userservice.dto.UserDto;
import com.finStream.userservice.entity.User;
import com.finStream.userservice.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserConsumerRMBQ {

    private final UserServiceImpl userService;

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeUserDto(UserDto userDto){
        log.info(String.format("consuming user -> %s", userDto.toString()));
        User user = userService.createUser(userDto);
        System.out.println(user);
    }


}
