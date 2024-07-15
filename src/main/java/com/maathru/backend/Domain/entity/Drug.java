package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "drugs")
@Getter
@Setter
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private Long drugId;
    @NotNull
    private String composition;
    @NotNull
    private String strength;
    @NotNull
    private String brandName;
    @NotNull
    private Integer quantity;
    @NotNull
    private String batchNumber;
    private String recommendedDose;
    @NotNull
    private LocalDate expiryDate;
    @NotNull
    private LocalDate manufacturedDate;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "updated_by_user_id")
    private User updatedBy;
    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreatedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforePersist() {
        setCreatedAt(now());
        setUpdatedAt(now());
    }

    @PreUpdate
    public void beforeUpdate() {
        setUpdatedAt(now());
    }
}