package com.maathru.backend.Application.config;

import com.maathru.backend.Domain.entity.MedicalRecord;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.eligible.BasicInfo;
import com.maathru.backend.Domain.entity.parent.ChildBirth;
import com.maathru.backend.Domain.entity.parent.ChildDetail;
import com.maathru.backend.Domain.entity.parent.OtherSituation;
import com.maathru.backend.External.repository.ChildBirthRepository;
import com.maathru.backend.External.repository.MedicalRecordRepository;
import com.maathru.backend.External.repository.OtherSituationRepository;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.External.repository.eligible.BasicInfoRepository;
import com.maathru.backend.External.repository.parent.ChildDetailRepository;
import com.maathru.backend.enumeration.Gender;
import com.maathru.backend.enumeration.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtherSituationRepository otherSituationRepository;

    @Autowired
    private BasicInfoRepository basicInfoRepository;

    @Autowired
    private ChildBirthRepository childBirthRepository;

    @Autowired
    private ChildDetailRepository childDetailRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public void run(String... args) throws Exception {
        // Existing users
//        User user1 = new User(7L, "user@maathru.com", "User", "Buddhika", Role.USER);
//        User user2 = new User(8L, "harini.fernando@example.com", "Harini", "Fernando", Role.PARENT);
//        User user3 = new User(9L, "nuwan.perera@example.com", "Nuwan", "Perera", Role.PARENT);
//        User user4 = new User(10L, "sanduni.weerasinghe@example.com", "Sanduni", "Weerasinghe", Role.PARENT);
//        User user5 = new User(11L, "ishan.kumara@example.com", "Ishan", "Kumara", Role.PARENT);
//        User user6 = new User(12L, "kavindi.silva@example.com", "Kavindi", "Silva", Role.PARENT);

//        // Ensure users are saved (only needed if not already in the DB)
////        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6));
//
        // Create and save OtherSituation records for each user
//        createAndSaveOtherSituation(user1, true, false, false, true, false, "BMI is high", "Heart disease diagnosed", null, null, null);
//        createAndSaveOtherSituation(user2, false, true, false, false, true, null, null, "Diabetes diagnosed", null, "Chronic kidney disease");
//        createAndSaveOtherSituation(user3, true, false, true, false, false, "BMI is low", null, null, "Currently being treated for malaria", null);
//        createAndSaveOtherSituation(user4, false, false, false, true, false, null, null, null, "Heart disease diagnosed", null);
//        createAndSaveOtherSituation(user5, true, true, false, false, true, "BMI is high", null, "Diabetes diagnosed", null, "Chronic kidney disease");
//        createAndSaveOtherSituation(user6, false, false, false, false, false, null, null, null, null, null);
//
//        System.out.println("Sample data populated!");

//

//
        // Create and save BasicInfo records
//        createAndSaveBasicInfo(user1, "Mary", "John", "123 Main St", "Colombo", "0771234567", "0711234567",
//                LocalDate.of(1990, 5, 20), LocalDate.of(1988, 10, 15), 24, 26, "Bachelor's Degree", "Master's Degree",
//                "Software Engineer", "Architect", 2, LocalDate.of(2014, 6, 15), 34, 36, "5 km");
//
//        createAndSaveBasicInfo(user2, "Harini", "Fernando", "456 Kandy Road", "Kandy", "0777654321", "0771231234",
//                LocalDate.of(1992, 8, 10), LocalDate.of(1988, 5, 25), 23, 25, "Diploma", "Master's Degree",
//                "Teacher", "Engineer", 1, LocalDate.of(2015, 8, 20), 32, 36, "10 km");
//
//        createAndSaveBasicInfo(user3, "Nuwan's Wife", "Nuwan", "789 Galle Road", "Galle", "0771112233", "0772233444",
//                LocalDate.of(1993, 7, 20), LocalDate.of(1990, 4, 15), 22, 25, "High School", "Bachelor's Degree",
//                "Housewife", "Doctor", 3, LocalDate.of(2016, 7, 10), 31, 34, "8 km");
//
//        createAndSaveBasicInfo(user4, "Sanduni", "Weerasinghe", "100 Hill Street", "Matara", "0781234567", "0709876543",
//                LocalDate.of(1995, 11, 5), LocalDate.of(1992, 12, 12), 20, 24, "Diploma", "PhD",
//                "Artist", "Scientist", 0, LocalDate.of(2018, 1, 15), 29, 32, "12 km");
//
//        createAndSaveBasicInfo(user5, "Ishan's Wife", "Ishan", "200 Lake View", "Anuradhapura", "0761231234", "0751239876",
//                LocalDate.of(1989, 6, 25), LocalDate.of(1987, 9, 18), 25, 27, "Graduate", "Graduate",
//                "Entrepreneur", "Entrepreneur", 4, LocalDate.of(2012, 6, 18), 35, 37, "15 km");
//
//        createAndSaveBasicInfo(user6, "Kavindi", "Silva", "300 Mountain View", "Nuwara Eliya", "0787654321", "0798765432",
//                LocalDate.of(1996, 4, 14), LocalDate.of(1993, 5, 20), 21, 24, "Certificate", "Bachelor's Degree",
//                "Nurse", "Engineer", 1, LocalDate.of(2017, 4, 10), 28, 31, "18 km");
//
//        System.out.println("BasicInfo sample data populated!");
//        if (childBirthRepository.count() == 0) {
//            seedChildBirths();
//            System.out.println("Child birth sample data populated!");
//        }

//        List<ChildDetail> childDetails = Arrays.asList(
//                createChildDetail(Gender.MALE, 3.2f, false, false, false, null, null, false, user1),
//                createChildDetail(Gender.FEMALE, 2.9f, true, true, false, null, null, true, user2),
//                createChildDetail(Gender.MALE, 3.5f, false, false, false, null, null, false, user3),
//                createChildDetail(Gender.FEMALE, 2.8f, true, true, true, LocalDate.of(2023, 5, 12), "Complications during birth", true, user4),
//                createChildDetail(Gender.MALE, 3.1f, false, false, false, null, null, false, user5),
//                createChildDetail(Gender.FEMALE, 2.7f, true, false, false, null, null, false, user6)
//        );
//
//        // Save Child Details to Database
//        childDetailRepository.saveAll(childDetails);
//        List<User> users = userRepository.findAll();
//        // Create medical records
//        User user1 = userRepository.findById(users.get(5).getUserId()).orElseThrow();
//        User user2 = userRepository.findById(users.get(6).getUserId()).orElseThrow();
//        User user3 = userRepository.findById(users.get(7).getUserId()).orElseThrow();
//
//        // Create medical records
//        MedicalRecord record1 = new MedicalRecord();
//        record1.setPatient(user1);
//        record1.setDisease("Flu");
//        record1.setMedicationGiven("Paracetamol, Antihistamines");
//        record1.setRemarks("Patient recovering well");
//        record1.setReferredTo("None");
//
//        MedicalRecord record2 = new MedicalRecord();
//        record2.setPatient(user2);
//        record2.setDisease("Asthma");
//        record2.setMedicationGiven("Inhaler, Bronchodilators");
//        record2.setRemarks("Requires follow-up in 2 weeks");
//        record2.setReferredTo("Pulmonologist");
//
//        MedicalRecord record3 = new MedicalRecord();
//        record3.setPatient(user3);
//        record3.setDisease("Diabetes");
//        record3.setMedicationGiven("Metformin, Insulin");
//        record3.setRemarks("Patient advised on diet control");
//        record3.setReferredTo("Endocrinologist");
//
//        // Save to repository
//        medicalRecordRepository.saveAll(List.of(record1, record2, record3));
//        System.out.println("Medical records seeded successfully!");
    }

    private void createAndSaveBasicInfo(
            User user,
            String womanName,
            String manName,
            String address,
            String location,
            String womanPhone,
            String manPhone,
            LocalDate womanDob,
            LocalDate manDob,
            int womanAgeMarried,
            int manAgeMarried,
            String womanEducationLevel,
            String manEducationLevel,
            String womanOccupation,
            String manOccupation,
            int children,
            LocalDate marriedDate,
            int womanAge,
            int manAge,
            String distance
    ) {
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setUser(user);
        basicInfo.setWomanName(womanName);
        basicInfo.setManName(manName);
        basicInfo.setAddress(address);
        basicInfo.setLocation(location);
        basicInfo.setWomanPhone(womanPhone);
        basicInfo.setManPhone(manPhone);
        basicInfo.setWomanDob(womanDob);
        basicInfo.setManDob(manDob);
        basicInfo.setWomanAgeMarried(womanAgeMarried);
        basicInfo.setManAgeMarried(manAgeMarried);
        basicInfo.setWomanEducationLevel(womanEducationLevel);
        basicInfo.setManEducationLevel(manEducationLevel);
        basicInfo.setWomanOccupation(womanOccupation);
        basicInfo.setManOccupation(manOccupation);
        basicInfo.setChildren(children);
        basicInfo.setMarriedDate(marriedDate);
        basicInfo.setWomanAge(womanAge);
        basicInfo.setManAge(manAge);
        basicInfo.setDistance(distance);

        basicInfoRepository.save(basicInfo);
    }

    private void createAndSaveOtherSituation(User user, boolean bmiLessOrHigh, boolean diabetes, boolean malaria, boolean heartDiseases, boolean kidneyDiseases,
                                             String bmiDetails, String heartDiseasesDetails, String diabetesDetails, String malariaDetails, String kidneyDiseasesDetails) {
        OtherSituation otherSituation = new OtherSituation();
        otherSituation.setUser(user);
        otherSituation.setBmiLessOrHigh(bmiLessOrHigh);
        otherSituation.setDiabetes(diabetes);
        otherSituation.setMalaria(malaria);
        otherSituation.setHeartDiseases(heartDiseases);
        otherSituation.setKidneyDiseases(kidneyDiseases);
        otherSituation.setBmiLessOrHighDetails(bmiDetails);
        otherSituation.setHeartDiseasesDetails(heartDiseasesDetails);
        otherSituation.setDiabetesDetails(diabetesDetails);
        otherSituation.setMalariaDetails(malariaDetails);
        otherSituation.setKidneyDiseasesDetails(kidneyDiseasesDetails);
        otherSituationRepository.save(otherSituation);
    }

    private void seedChildBirths() {
        List<ChildBirth> childBirths = List.of(
                createChildBirth("Colombo General Hospital", LocalDate.of(2023, 10, 20), "2023-10-25",
                        "Dr. Perera", "Normal", "Healthy", 8L),
                createChildBirth("Kandy Hospital", LocalDate.of(2023, 9, 15), "2023-09-20",
                        "Dr. Silva", "C-Section", "Healthy", 9L),
                createChildBirth("Galle Teaching Hospital", LocalDate.of(2023, 8, 10), "2023-08-15",
                        "Dr. Fernando", "Normal", "Healthy", 10L),
                createChildBirth("Matara Base Hospital", LocalDate.of(2023, 7, 5), "2023-07-10",
                        "Dr. Kumara", "Normal", "Healthy", 11L),
                createChildBirth("Jaffna General Hospital", LocalDate.of(2023, 6, 20), "2023-06-25",
                        "Dr. De Silva", "Complicated", "Premature", 12L)
        );

        childBirthRepository.saveAll(childBirths);
    }

    private ChildBirth createChildBirth(String birthPlace, LocalDate dob, String dateReleased,
                                        String doneBy, String resultOfPregnancy, String statusOfPregnancy,
                                        Long userId) {
        ChildBirth childBirth = new ChildBirth();
        childBirth.setBirthPlace(birthPlace);
        childBirth.setDob(dob);
        childBirth.setDateReleased(dateReleased);
        childBirth.setDoneBy(doneBy);
        childBirth.setResultOfPregnancy(resultOfPregnancy);
        childBirth.setStatusOfPregnancy(statusOfPregnancy);
        childBirth.setUser(userRepository.findById(userId).orElse(null));
        return childBirth;
    }

    private ChildDetail createChildDetail(Gender sex, float birthWeight, boolean prematureBirth,
                                          boolean complicationsAtBirth, boolean motherDead, LocalDate motherDeadDate,
                                          String motherDeadCause, boolean investigated, User user) {
        ChildDetail childDetail = new ChildDetail();
        childDetail.setSex(sex);
        childDetail.setBirthWeight(birthWeight);
        childDetail.setPrematureBirth(prematureBirth);
        childDetail.setComplicationsAtBirth(complicationsAtBirth);
        childDetail.setMotherDead(motherDead);
        childDetail.setMotherDeadDate(motherDeadDate);
        childDetail.setMotherDeadCause(motherDeadCause);
        childDetail.setInvestigated(investigated);
        childDetail.setUser(user);
        return childDetail;
    }

}
