package com.prabeshcodes.student.repository;

import com.prabeshcodes.student.model.FavoriteStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteStoreRepository extends JpaRepository<FavoriteStore, Long> {
    List<FavoriteStore> findByUserId(Long userId);
}