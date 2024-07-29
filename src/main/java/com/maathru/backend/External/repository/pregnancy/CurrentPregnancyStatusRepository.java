
package com.maathru.backend.External.repository.pregnancy;

import com.maathru.backend.Domain.entity.pregnancycard.CurrentPregnancyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentPregnancyStatusRepository extends JpaRepository<CurrentPregnancyStatus, Long> {
}