package com.final_project.Store.service;

import com.final_project.Store.dto.StoreRegisterFormDto;
import com.final_project.Store.entity.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class StoreServiceTest {

    @Autowired
    StoreService storeService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Store createStore() {

        StoreRegisterFormDto storeRegisterFormDto = new StoreRegisterFormDto();

        storeRegisterFormDto.setStoreId("test");
        storeRegisterFormDto.setStorePwd("test1234");
        storeRegisterFormDto.setStoreName("테스트");
        storeRegisterFormDto.setStoreCorporateNum(111111111);
        storeRegisterFormDto.setStoreEmail("test1234@naver.com");
        storeRegisterFormDto.setStoreZipCode("22222");
        storeRegisterFormDto.setStoreAddress("인천광역시 미추홀구");
        storeRegisterFormDto.setStorePhone("010-0000-0000");

        return Store.createStore(storeRegisterFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("가게 회원 가입 테스트")
    public void saveStoreTest() {

        Store store = createStore();
        Store saveStore = storeService.saveStore(store);

        assertEquals(store.getStoreId(), saveStore.getStoreId());
        assertEquals(store.getStorePwd(), saveStore.getStorePwd());
        assertEquals(store.getStoreName(), saveStore.getStoreName());
        assertEquals(store.getStoreCorporateNum(), saveStore.getStoreCorporateNum());
        assertEquals(store.getStoreEmail(), saveStore.getStoreEmail());
        assertEquals(store.getStoreZipCode(), saveStore.getStoreZipCode());
        assertEquals(store.getStoreAddress(), saveStore.getStoreAddress());
        assertEquals(store.getStorePhone(), saveStore.getStorePhone());
        assertEquals(store.getRole(), saveStore.getRole());
    }

    @Test
    @DisplayName("가게 중복 회원 가입 테스트")
    public void saveDuplicateStoreTest() {

        Store store1 = createStore();
        Store store2 = createStore();

        storeService.saveStore(store1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            storeService.saveStore(store2);
        });

        assertEquals("이미 등록된 가게입니다", e.getMessage());
    }
}
