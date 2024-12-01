package com.maathru.backend.Domain.service;

import com.maathru.backend.Domain.entity.PregnancyCard;
import com.maathru.backend.External.repository.ChildBirthRepository;
import com.maathru.backend.External.repository.OtherSituationRepository;
import com.maathru.backend.External.repository.PregnancyCardRepository;
import com.maathru.backend.External.repository.eligible.BasicInfoRepository;
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

    public List<Map<String, Object>> getPregnancyCountByRegion() {
        return pregnancyCardRepository.getPregnancyCountByRegion();
    }

    public List<Map<String, Object>> getPregnancyCountByAge() {
        return basicInfoRepository.getPregnancyCountByAge();
    }

    public Map<Integer, Long> getBirthsCountByMonth() {
        // Fetch data from the repository
        List<Object[]> results = childBirthRepository.getBirthsCountByMonth();

        // Convert the List<Object[]> to a Map<Integer, Long>
        return results.stream()
                .collect(Collectors.toMap(
                        result -> (Integer) result[0], // The month (e.g., 1 for January)
                        result -> (Long) result[1]      // The count of births for that month
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
}
