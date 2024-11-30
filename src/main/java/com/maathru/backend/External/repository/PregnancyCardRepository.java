package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Parent;
import com.maathru.backend.Domain.entity.PregnancyCard;
import com.maathru.backend.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PregnancyCardRepository extends JpaRepository<PregnancyCard, Long> {
    List<PregnancyCard> findByParent(Parent parent);
}