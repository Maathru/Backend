package com.maathru.backend.Domain.service;

import com.maathru.backend.Domain.entity.PregnancyCard;
import com.maathru.backend.Domain.entity.parent.ChildDetail;
import com.maathru.backend.External.repository.ChildBirthRepository;
import com.maathru.backend.External.repository.OtherSituationRepository;
import com.maathru.backend.External.repository.PregnancyCardRepository;
import com.maathru.backend.External.repository.eligible.BasicInfoRepository;
import com.maathru.backend.External.repository.parent.ChildDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnalyticsService {
    private final PregnancyCardRepository pregnancyCardRepository;
    private final OtherSituationRepository otherSituationRepository;
    private final BasicInfoRepository basicInfoRepository;
    private final ChildBirthRepository childBirthRepository;
    private final ChildDetailRepository childDetailRepository;

    public List<Map<String, Object>> getPregnancyCountByRegion() {
        return pregnancyCardRepository.getPregnancyCountByRegion();
    }

    public List<Map<String, Object>> getPregnancyCountByAge() {
        return basicInfoRepository.getPregnancyCountByAge();
    }

    public Map<Integer, Long> getBirthsCountByMonth() {
        List<Object[]> results = childBirthRepository.getBirthsCountByMonth();

        return results.stream()
                .collect(Collectors.toMap(
                        result -> (Integer) result[0],
                        result -> (Long) result[1]
                ));
    }


    public Map<String, Long> getHealthConditionsStats() {
        Map<String, Long> healthStats = new HashMap<>();

        healthStats.put("diabetes", otherSituationRepository.countByDiabetesTrue());
        healthStats.put("malaria", otherSituationRepository.countByMalariaTrue());
        healthStats.put("heartDiseases", otherSituationRepository.countByHeartDiseasesTrue());
        healthStats.put("kidneyDiseases", otherSituationRepository.countByKidneyDiseasesTrue());

        return healthStats;
    }


    public Map<String, Long> analyzeBMIByCategory() {
        List<PregnancyCard> pregnancyCards = pregnancyCardRepository.findAll();

        Map<String, Long> bmiCategories = new HashMap<>();

        for (PregnancyCard card : pregnancyCards) {
            String category = categorizeBMI(card.getBmi());
            bmiCategories.put(category, bmiCategories.getOrDefault(category, 0L) + 1);
        }

        return bmiCategories;
    }

    // Method to categorize BMI values
    private String categorizeBMI(Double bmi) {
        if (bmi == null || bmi <= 0) {
            return "Invalid BMI";
        }
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public List<Float> getBirthWeights() {
        return childDetailRepository.findAll()
                .stream()
                .map(ChildDetail::getBirthWeight)
                .collect(Collectors.toList());
    }
}
