package com.final_project.Store.entity;

import com.final_project.Store.constant.Role;
import com.final_project.Store.dto.StoreRegisterFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "store", uniqueConstraints = {@UniqueConstraint(name = "storeIdNameUnique", columnNames = {"storeId", "storeName"})})
@Getter
@Setter
@ToString
public class Store {

    @Id
    @SequenceGenerator(name = "STORE_SEQUENCE_GEN", sequenceName = "store_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "storeNum")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_SEQUENCE_GEN")
    private Long storeNum;

    @Column(unique = true)
    private String storeId;

    @Column(unique = true)
    private String storeName;

    private String storePwd;
    private int storeCorporateNum;
    private String storeEmail;
    private String storeZipCode;
    private String storeAddress;
    private String storePhone;

    @CreationTimestamp
    private LocalDateTime storeRegDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Store createStore(StoreRegisterFormDto storeRegisterFormDto, PasswordEncoder passwordEncoder) {
        Store store = new Store();

        store.setStoreId(storeRegisterFormDto.getStoreId());

        String storePwd = passwordEncoder.encode(storeRegisterFormDto.getStorePwd());
        store.setStorePwd(storePwd);

        store.setStoreName(storeRegisterFormDto.getStoreName());

        store.setStoreCorporateNum(storeRegisterFormDto.getStoreCorporateNum());

        store.setStoreEmail(storeRegisterFormDto.getStoreEmail());

        store.setStoreZipCode(storeRegisterFormDto.getStoreZipCode());

        store.setStoreAddress(storeRegisterFormDto.getStoreAddress());

        store.setStorePhone(storeRegisterFormDto.getStorePhone());

        store.setRole(Role.STORE);

        return store;

    }
}
