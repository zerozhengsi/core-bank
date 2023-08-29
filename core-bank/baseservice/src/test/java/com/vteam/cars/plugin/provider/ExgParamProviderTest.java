package com.vteam.cars.plugin.provider;

import com.vteam.cars.entity.vo.FspaExgMVo;
import com.vteam.cars.service.fspa.FspaExgMService;
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
class ExgParamProviderTest {
    @Autowired
    ExgParamProvider exgParamProvider;


    @Test
    void testGet() {
        String result = exgParamProvider.get("zh_CN","language","zh_CN");
        Assertions.assertEquals("简体中文", result);

        result = exgParamProvider.get("zh_CN","language","简体中文");
        Assertions.assertEquals("zh_CN", result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme