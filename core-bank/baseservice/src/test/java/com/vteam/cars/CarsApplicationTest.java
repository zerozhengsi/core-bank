package com.vteam.cars;

import com.vteam.cars.plugin.activemq.producer.MessageProducer;
import com.vteam.cars.plugin.message.model.MessageModel;
import jakarta.jms.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * @className: CarsApplicationTest
 * @Description: TODO
 * @version: v1.８.0
 * @author: care.zheng
 * @date: 2023/8/24 16:50
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarsApplicationTest {

    @Autowired
    MessageProducer messageProducer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSend() {
        messageProducer.send(new MessageModel());
    }
}
