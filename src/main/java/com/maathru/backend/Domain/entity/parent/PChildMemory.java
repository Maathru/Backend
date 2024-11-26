package com.maathru.backend.Domain.entity.parent;

import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class PChildMemory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private int memoryId;

    private String title;
    private String description;
    private Date createDate;

    @OneToOne
    @JoinColumn(name = "mother_id", updatable = false, nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private ChildDetail child;
}
