package com.prabeshcodes.student.repository;

import com.prabeshcodes.student.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStoreId(Long storeId);
}