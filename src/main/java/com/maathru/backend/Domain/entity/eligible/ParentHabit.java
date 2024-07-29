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
@Table(name = "parent_habits")
public class ParentHabit extends Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true, nullable = false)
    private long id;

    @NotNull
    private boolean manCigarettes;
    @NotNull
    private boolean manAlcohol;
    @NotNull
    private boolean manDrugs;
    @NotNull
    private boolean manSmoke;
    @NotNull
    private boolean manViolent;
    @NotNull
    private boolean manRituals;
    @NotNull
    private boolean manExercise;
    @NotNull
    private boolean manHobbies;

    @NotNull
    private boolean womanCigarettes;
    @NotNull
    private boolean womanAlcohol;
    @NotNull
    private boolean womanDrugs;
    @NotNull
    private boolean womanSmoke;
    @NotNull
    private boolean womanViolent;
    @NotNull
    private boolean womanRituals;
    @NotNull
    private boolean womanExercise;
    @NotNull
    private boolean womanHobbies;

    private String cigarettesDetails;
    private String alcoholDetails;
    private String drugsDetails;
    private String smokeDetails;
    private String violentDetails;
    private String ritualsDetails;
    private String exerciseDetails;
    private String hobbiesDetails;

    @OneToOne
    @JoinColumn(name = "user_user_id", updatable = false, nullable = false, unique = true)
    private User user;
}
