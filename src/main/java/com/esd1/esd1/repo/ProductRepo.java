package com.esd1.esd1.repo;

import com.esd1.esd1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
    Optional<Product> findByPrice(float price);

    void deleteByName(String name);

    @Query(value="select * from product where product_price between 15 and 30 order by product_price limit 2;", nativeQuery=true)
    List<Product> findTop2Between15And30();

}
