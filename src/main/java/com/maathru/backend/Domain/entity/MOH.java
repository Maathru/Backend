package com.maathru.backend.Domain.entity;

import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.District;
import com.maathru.backend.enumeration.Province;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "moh")
@Getter
@Setter
public class MOH extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private Long mohId;

    @Column(unique = true)
    private String mohRegistrationNumber;

    @Enumerated(EnumType.STRING)
    private Province province;
    @Enumerated(EnumType.STRING)
    private District district;
    @Enumerated(EnumType.STRING)
    private Area area;

    private String addressLine1;
    private String street;
    private String city;

    @Column(unique = true)
    private String telephoneNumber;
    @Column(unique = true)
    private String faxNumber;
    private Long population;

    @OneToOne
    @JoinColumn(name = "current_head_id")
    private Employee currentHead;

    public MOH(Long mohId, String mohRegistrationNumber, Province province, District district, Area area, String addressLine1, String street, String city, String telephoneNumber, String faxNumber, Long population) {
        super();
        this.mohId = mohId;
        this.mohRegistrationNumber = mohRegistrationNumber;
        this.province = province;
        this.district = district;
        this.area = area;
        this.addressLine1 = addressLine1;
        this.street = street;
        this.city = city;
        this.telephoneNumber = telephoneNumber;
        this.faxNumber = faxNumber;
        this.population = population;
    }

    public MOH() {

    }
}