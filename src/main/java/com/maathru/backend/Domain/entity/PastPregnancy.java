package com.maathru.backend.Domain.entity;

import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import com.maathru.backend.enumeration.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "past_pregnancys")
public class PastPregnancy extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String result;

    @ManyToOne
    @JoinColumn(name = "basic_info_id")
    private BasicInfo basicInfo;
}
