package com.maathru.backend.Domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medical_record")
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, unique = true, nullable = false)
  private Long recordId;

  @ManyToOne(optional = false)
  @JoinColumn(name = "patient_id", nullable = false)
  private User patient;

  @Column(name = "disease")
  private String disease;

  @Column(name = "medication_given")
  private String medicationGiven;

  @Column(name = "remarks")
  private String remarks;

  @Column(name = "referred_to")
  private String referredTo;

}