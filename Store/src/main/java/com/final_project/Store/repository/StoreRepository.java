package com.final_project.Store.repository;

import com.final_project.Store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreId(String storeId);

}
