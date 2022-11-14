package com.final_project.Store.controller;

import com.final_project.Store.dto.StoreRegisterFormDto;
import com.final_project.Store.entity.Store;
import com.final_project.Store.service.StoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class StoreControllerTest {

    @Autowired
    private StoreService storeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Store createStore(String storeId, String storePwd) {
        StoreRegisterFormDto storeRegisterFormDto = new StoreRegisterFormDto();
        storeRegisterFormDto.setStoreId(storeId);
        storeRegisterFormDto.setStorePwd(storePwd);
        storeRegisterFormDto.setStoreName("테스트");
        storeRegisterFormDto.setStoreCorporateNum(11111111);
        storeRegisterFormDto.setStoreEmail("test1234@naver.com");
        storeRegisterFormDto.setStoreZipCode("22222");
        storeRegisterFormDto.setStoreAddress("인천광역시");
        storeRegisterFormDto.setStorePhone("010-0000-0000");
        Store store = Store.createStore(storeRegisterFormDto, passwordEncoder);
        return storeService.saveStore(store);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception {
        String storeId = "test1234";
        String storePwd = "test1234";
        this.createStore(storeId, storePwd);

        mockMvc.perform(formLogin().userParameter("storeId")
                .loginProcessingUrl("/stores/login")
                .user(storeId).password(storePwd))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }
}
