package vn.hieesu.bt09.repo;


import vn.hieesu.bt09.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}