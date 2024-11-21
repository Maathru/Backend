package com.maathru.backend.Domain.entity.parent;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "current_pregnancies")
public class CurrentPregnancy extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    private boolean pregnancyUnder_20_andOver_35;
    private boolean moreThan5_pregnancies;
    private boolean preeclampsia;
    private boolean antepartumVaginalBleeding;
    private boolean multipleBirths;
    private boolean casualPositions;
    private boolean doDOfTheChildIsNotSpecified;
    private boolean otherThingsToConsider;

    private String pregnancyUnder_20_andOver_35Details;
    private String moreThan5_pregnanciesDetails;
    private String preeclampsiaDetails;
    private String antepartumVaginalBleedingDetails;
    private String multipleBirthsDetails;
    private String casualPositionsDetails;
    private String doDOfTheChildIsNotSpecifiedDetails;
    private String otherThingsToConsiderDetails;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
