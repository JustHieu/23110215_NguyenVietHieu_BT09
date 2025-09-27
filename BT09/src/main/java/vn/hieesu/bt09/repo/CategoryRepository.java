package vn.hieesu.bt09.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.hieesu.bt09.entity.Category;
public interface CategoryRepository extends JpaRepository<Category, Long> {}
