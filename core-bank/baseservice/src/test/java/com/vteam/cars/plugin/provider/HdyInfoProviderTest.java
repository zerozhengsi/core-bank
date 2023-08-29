package com.vteam.cars.plugin.provider;

import com.vteam.cars.entity.model.FipaHdyM;
import com.vteam.cars.service.fipa.FipaHdyMService;
import com.vteam.vtarm.cache.MapContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HdyInfoProviderTest {
    @Autowired
    HdyInfoProvider hdyInfoProvider;


    @Test
    void testGet() {

        String result = hdyInfoProvider.get("2023-9-29");
        Assertions.assertTrue(Boolean.valueOf(result));

        result = hdyInfoProvider.get("2023-9-28");
        Assertions.assertEquals("",result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme