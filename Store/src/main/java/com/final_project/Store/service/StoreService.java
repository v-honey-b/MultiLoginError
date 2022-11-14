package com.final_project.Store.service;

import com.final_project.Store.entity.Store;
import com.final_project.Store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService implements UserDetailsService {

    private final StoreRepository storeRepository;

    public Store saveStore(Store store) {

        validateDuplicateStore(store);

        return storeRepository.save(store);

    }

    private void validateDuplicateStore(Store store) {

        Store findStore = storeRepository.findByStoreId(store.getStoreId());

        if (findStore != null) {
            throw new IllegalStateException("이미 등록된 가게입니다");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String storeId) throws UsernameNotFoundException {

        Store store = storeRepository.findByStoreId(storeId);

        if (store == null) {
            throw new UsernameNotFoundException(storeId);
        }

        return User.builder()
                .username(store.getStoreId())
                .password(store.getStorePwd())
                .roles(store.getRole().toString())
                .build();
    }
}
