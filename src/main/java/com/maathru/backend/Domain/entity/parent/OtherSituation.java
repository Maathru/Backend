package com.maathru.backend.Domain.entity.parent;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "other_situations")
public class OtherSituation extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    private boolean bmiLessOrHigh;
    private boolean diabetes;
    private boolean malaria;
    private boolean heartDiseases;
    private boolean kidneyDiseases;

    private String bmiLessOrHighDetails;
    private String diabetesDetails;
    private String malariaDetails;
    private String heartDiseasesDetails;
    private String kidneyDiseasesDetails;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
