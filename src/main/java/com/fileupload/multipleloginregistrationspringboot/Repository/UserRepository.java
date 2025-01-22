package com.fileupload.multipleloginregistrationspringboot.Repository;

import com.fileupload.multipleloginregistrationspringboot.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailToken(String token);

}
