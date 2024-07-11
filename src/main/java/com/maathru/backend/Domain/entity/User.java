package com.maathru.backend.Domain.entity;

import com.maathru.backend.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long userId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @NotNull
    private String password;
    private Integer loginAttempts;
    private LocalDateTime lastLogin;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
//    @NotNull
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "updated_by_user_id")
//    @NotNull
    private User updatedBy;
    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreatedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

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
