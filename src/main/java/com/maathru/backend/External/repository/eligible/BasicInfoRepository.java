package com.maathru.backend.External.repository.eligible;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicInfoRepository extends JpaRepository<BasicInfo, Long> {
    Optional<BasicInfo> findByUser(User currentUser);
}
