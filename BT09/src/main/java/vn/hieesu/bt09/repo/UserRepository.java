package vn.hieesu.bt09.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.hieesu.bt09.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {}
