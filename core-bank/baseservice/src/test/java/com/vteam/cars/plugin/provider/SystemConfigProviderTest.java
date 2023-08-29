package com.vteam.cars.plugin.provider;

import com.vteam.cars.entity.vo.FipaSysMVo;
import com.vteam.cars.service.fipa.FipaSysMService;
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
class SystemConfigProviderTest {
    @Autowired
    SystemConfigProvider systemConfigProvider;

    @Test
    void testGet() {
        String result = systemConfigProvider.get("W_SYSTEM_LANGUAGE");
        Assertions.assertEquals("zh_CN", result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme