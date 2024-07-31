package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u.userId FROM User u WHERE u.email = :email AND u.role = :role")
    Optional<Long> findUserIdByEmailAndRole(@Param("email") String email, @Param("role") Role role);

    @Query("SELECT u FROM BasicInfo b JOIN b.user u WHERE u.userId = :userId AND b.id = :basicInfoId AND u.role = :role")
    Optional<User> findByUserIdAndBasicInfoIdAndRole(@Param("userId") long userId, @Param("basicInfoId") long basicInfoId, @Param("role") Role role);
}
