package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "parent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parentId;

    @OneToOne(fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime registeredTime;

    @Column(unique = true)
    private String phone;
    private String serialNo;
    private String registrationNo;
    private LocalDate parentBirthdate;

    @Column(name = "address_line_1")
    private String addressLine1;
    private String street;
    private String city;
    private String location;
    private Integer noOfLivingChildren;
    private Integer gravidity;
    private String poa;
    private LocalDate edd;
    private Boolean protectionAgainstTetanus;
    private Boolean protectionAgainstRubella;
    private String riskCondition;

    @Column(name = "sugar_level_12_weeks")
    private Double sugarLevel12Weeks;

    @Column(name = "sugar_level_24_28_weeks")
    private Double sugarLevel2428Weeks;

    @Column(name = "hemoglobin_level_12_weeks")
    private Double hemoglobinLevel12Weeks;

    @Column(name = "hemoglobin_level_26_28_weeks")
    private Double hemoglobinLevel2628Weeks;
    private String bloodGroup;

    @Column(name = "vdrl_test_12_weeks")
    private Boolean vdrlTest12Weeks;

    @Column(name = "vdrl_test_12_weeks_after")
    private Boolean vdrlTest12WeeksAfter;
    private Boolean hivTest;
    private String gramaNiladhariDivision;

    @Column(name = "bmi_before_12")
    private Double bmiBefore12;
    private String modeOfDelivery;
    private Double birthWeight;
    private String birthRegistrationNumber;
    private LocalDate dateOfRegister;
    private String remarks;
}