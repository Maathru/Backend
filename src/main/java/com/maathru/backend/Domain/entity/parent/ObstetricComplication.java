package com.maathru.backend.Domain.entity.parent;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "obstetric_complications")
public class ObstetricComplication extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    private boolean crackedAround;
    private boolean postpartumBleeding;
    private boolean trappedAura;
    private boolean anyCutsAround;
    private boolean longDelivery;
    private boolean otherComplications;
    private boolean vitaminADose;
    private boolean rubellaVaccine;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
