package vn.hieesu.bt09.repo;


import vn.hieesu.bt09.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.*;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByPriceAsc();


    @Query("select p from Product p join p.categories c where c.id = :cid")
    List<Product> findByCategoryId(@Param("cid") Long categoryId);
}