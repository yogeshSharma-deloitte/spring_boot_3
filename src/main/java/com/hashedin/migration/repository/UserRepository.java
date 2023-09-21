package com.hashedin.migration.repository;

import com.hashedin.migration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository interface for User entity
 *
 * @author MstfDryl
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findByUsername(String username);

Optional<User> findByEmail(String email);

}