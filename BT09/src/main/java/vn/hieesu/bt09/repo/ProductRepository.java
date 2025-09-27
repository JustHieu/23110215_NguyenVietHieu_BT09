// vn/hieesu/bt09/repo/ProductRepository.java
package vn.hieesu.bt09.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.hieesu.bt09.entity.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findByCategories_Id(Long categoryId); // products của 1 category
}
