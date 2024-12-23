package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_basic_info")
public class BasicInfo extends Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;
    private String womanName;
    private String manName;
    private String address;
    private String location;
    private String womanPhone;
    private String manPhone;
    private LocalDate womanDob;
    private LocalDate manDob;
    private int womanAgeMarried;
    private int manAgeMarried;
    private String womanEducationLevel;
    private String manEducationLevel;
    private String womanOccupation;
    private String manOccupation;
    private int children;
    private LocalDate marriedDate;
    private int womanAge;
    private int manAge;
    private String distance;

    @ManyToOne
    @JoinColumn(name = "region_region_id")
    private Region region;

    @OneToMany(mappedBy = "basicInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PastPregnancy> pastPregnancies = new ArrayList<>();

    public void setPastPregnancies(List<PastPregnancy> pastPregnancies) {
        this.pastPregnancies.clear();
        if (pastPregnancies != null) {
            this.pastPregnancies.addAll(pastPregnancies);
        }
    }

    @Getter
    @OneToMany(mappedBy = "basicInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FamilyPlanningMethod> familyPlanningMethods = new ArrayList<>();

    public void setFamilyPlanningMethods(List<FamilyPlanningMethod> familyPlanningMethods) {
        this.familyPlanningMethods.clear();
        if (familyPlanningMethods != null) {
            this.familyPlanningMethods.addAll(familyPlanningMethods);
        }
    }

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
