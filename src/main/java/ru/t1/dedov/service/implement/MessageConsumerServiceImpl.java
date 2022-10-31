package ru.t1.dedov.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.t1.dedov.service.interfaces.MessageConsumerService;

@Service
@RequiredArgsConstructor
public class MessageConsumerServiceImpl implements MessageConsumerService {

    @KafkaListener(topics = "${kafka.secondTopicName}", groupId = "${kafka.groupId}")
    public void listenGroupBase(String message) {
        System.out.println(message);
    }
}
