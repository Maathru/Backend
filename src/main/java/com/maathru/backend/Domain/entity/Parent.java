package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "parent")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long parentId;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "region_region_id")
    private Region region;

    @Column(name = "registered_date", nullable = false)
    private LocalDate registeredDate;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "registration_no")
    private String registrationNo;

    @Column(name = "parent_birthdate", nullable = false)
    private LocalDate parentBirthdate;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "location")
    private String location;

    @Column(name = "no_of_living_children")
    private Integer noOfLivingChildren;

    @Column(name = "gravidity")
    private Integer gravidity;

    @Column(name = "poa")
    private String poa;

    @Column(name = "edd")
    private LocalDate edd;

    @Column(name = "protection_against_tetanus")
    private Boolean protectionAgainstTetanus;

    @Column(name = "protection_against_rubella")
    private Boolean protectionAgainstRubella;

    @Column(name = "risk_condition")
    private String riskCondition;

    @Column(name = "sugar_level_12_weeks")
    private Double sugarLevel12Weeks;

    @Column(name = "sugar_level_24_28_weeks")
    private Double sugarLevel2428Weeks;

    @Column(name = "hemoglobin_level_12_weeks")
    private Double hemoglobinLevel12Weeks;

    @Column(name = "hemoglobin_level_26_28_weeks")
    private Double hemoglobinLevel2628Weeks;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "vdrl_test_12_weeks")
    private Boolean vdrlTest12Weeks;

    @Column(name = "vdrl_test_12_weeks_after")
    private Boolean vdrlTest12WeeksAfter;

    @Column(name = "hiv_test")
    private Boolean hivTest;

    @Column(name = "grama_niladhari_division")
    private String gramaNiladhariDivision;

    @Column(name = "bmi_before_12")
    private Double bmiBefore12;

    @Column(name = "mode_of_delivery")
    private String modeOfDelivery;

    @Column(name = "birth_weight")
    private Double birthWeight;

    @Column(name = "birth_registration_number")
    private String birthRegistrationNumber;

    @Column(name = "date_of_register")
    private LocalDate dateOfRegister;

    @Column(name = "remarks")
    private String remarks;
}
