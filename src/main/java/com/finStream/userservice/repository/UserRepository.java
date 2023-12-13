package com.finStream.userservice.repository;

import com.finStream.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * The UserRepository interface provides methods for performing database operations
 * related to the User entity. It extends the JpaRepository interface, which offers
 * standard CRUD (Create, Read, Update, Delete) operations for User entities.
 *
 * @param  -> <User>   The User entity type managed by this repository.
 * @param ->  <UUID>   The type of the unique identifier (primary key) for User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Retrieves an optional User entity by searching for a user with the given username.
     *
     * @param email The username to search for.
     * @return An Optional containing the User entity if found, or an empty Optional if not found.
     */
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
}
