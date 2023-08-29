package com.vteam.cars.service.common;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.service.fipa.FipaNumMService;
import com.vteam.cars.service.fspa.FspaSeqMService;
import com.vteam.cars.sql.QueryExecutor;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.cache.StringValueContainer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommonServiceTest {
    @Autowired
    CommonService commonService;


    @Test
    void testGetMaxNum() {
        Integer result = commonService.getMaxNum(Integer.valueOf(1), "contract-num");
        log.info("MaxNum:"+result);
    }

    @Test
    void testGetMaxRefcodeByTableName() {
        int result = commonService.getMaxRefcodeByTableName("FIPA_SYS_M");
        log.info("MaxRefcodeByTableName:"+result);
    }


}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme