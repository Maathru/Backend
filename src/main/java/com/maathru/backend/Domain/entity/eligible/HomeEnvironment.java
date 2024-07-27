package com.maathru.backend.Domain.entity.eligible;

import com.maathru.backend.Domain.entity.Auditable;
import com.maathru.backend.Domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "home_environment")
public class HomeEnvironment extends Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    @NotNull
    private boolean manSafeWater;
    @NotNull
    private boolean manToilet;
    @NotNull
    private boolean manGarbage;
    @NotNull
    private boolean manHazardous;
    @NotNull
    private boolean manChemicals;
    @NotNull
    private boolean manRadioactive;
    @NotNull
    private boolean manExtremeHeat;
    @NotNull
    private boolean manSmokeCooking;
    @NotNull
    private boolean manNoisy;
    @NotNull
    private boolean manSaving;

    @NotNull
    private boolean womanSafeWater;
    @NotNull
    private boolean womanToilet;
    @NotNull
    private boolean womanGarbage;
    @NotNull
    private boolean womanHazardous;
    @NotNull
    private boolean womanChemicals;
    @NotNull
    private boolean womanRadioactive;
    @NotNull
    private boolean womanExtremeHeat;
    @NotNull
    private boolean womanSmokeCooking;
    @NotNull
    private boolean womanNoisy;
    @NotNull
    private boolean womanSaving;

    private String waterDetails;
    private String toiletDetails;
    private String garbageDetails;
    private String hazardousDetails;
    private String chemicalsDetails;
    private String radioactiveDetails;
    private String extremeHeatDetails;
    private String smokeCookingDetails;
    private String noisyDetails;
    private String savingDetails;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
