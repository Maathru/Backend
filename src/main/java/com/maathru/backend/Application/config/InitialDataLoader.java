package com.maathru.backend.Application.config;

import com.maathru.backend.Domain.entity.*;
import com.maathru.backend.External.repository.*;
import com.maathru.backend.enumeration.Area;
import com.maathru.backend.enumeration.Gender;
import com.maathru.backend.enumeration.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.maathru.backend.enumeration.District.COLOMBO;
import static com.maathru.backend.enumeration.Province.WESTERN;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class InitialDataLoader {

    private final UserRepository userRepository;
    private final MOHRepository mohRepository;
    private final EmployeeRepository employeeRepository;
    private final RegionRepository regionRepository;

    @Bean
    public CommandLineRunner loadData(BlogRepository blogRepository) {
        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Create and save users
            List<User> users = List.of(
                    new User(1L, "admin@maathru.com", "Admin", "Buddhika", Role.ADMIN),
                    new User(2L, "doctor@maathru.com", "Doctor", "Buddhika", Role.DOCTOR),
                    new User(3L, "midwife@maathru.com", "Midwife", "Buddhika", Role.MIDWIFE),
                    new User(4L, "parent@maathru.com", "Parent", "Buddhika", Role.PARENT),
                    new User(5L, "eligible@maathru.com", "Eligible", "Buddhika", Role.ELIGIBLE),
                    new User(6L, "pending@maathru.com", "Pending", "Buddhika", Role.PENDING),
                    new User(7L, "user@maathru.com", "User", "Buddhika", Role.USER)
            );

            for (User user : users) {
                if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
                    user.setAccountNonExpired(true);
                    user.setAccountNonLocked(true);
                    user.setCreatedAt(LocalDateTime.now());
                    user.setEnabled(true);
                    user.setLoginAttempts(0);
                    user.setPassword(passwordEncoder.encode("zaq123"));
                    user.setUpdatedAt(LocalDateTime.now());
                    user.setCreatedBy(user);
                    user.setUpdatedBy(user);
                    userRepository.save(user);
                    log.info("{} user created successfully.", user.getFirstName());
                } else {
                    log.warn("{} user already exists.", user.getFirstName());
                }
            }

            // Get the admin user
            User adminUser = userRepository.findByEmail("admin@maathru.com").orElseThrow(() -> new RuntimeException("Admin user not found"));

            // Create and save MOHs
            List<MOH> mohList = List.of(
                    new MOH(1L, "M/W/1", WESTERN, COLOMBO, Area.NUGEGODA, "No 31", "Temple Road", "Udahamulla", "0712345678", "0712345678", 8000L),
                    new MOH(2L, "M/W/2", WESTERN, COLOMBO, Area.MAHARAGAMA, "No 20/4", "High level Road", "Maharagama", "0723456789", "072345679", 6000L),
                    new MOH(3L, "M/W/3", WESTERN, COLOMBO, Area.BORALESGAMUWA, "No 11", "Piliyndala Road", "Bokundara", "0722345678", "0722345678", 1000L),
                    new MOH(4L, "M/W/4", WESTERN, COLOMBO, Area.PILIYANDALA, "No 98/5", "Samagi Mawatha", "Kesbewa", "0742345678", "0742345678", 3000L)
            );

            for (MOH moh : mohList) {
                if (mohRepository.findByMohRegistrationNumber(moh.getMohRegistrationNumber()).isEmpty()) {
                    moh.setCreatedBy(adminUser);
                    moh.setUpdatedBy(adminUser);
                    moh.setCreatedAt(LocalDateTime.now());
                    moh.setUpdatedAt(LocalDateTime.now());
                    mohRepository.save(moh);
                    log.info("MOH {} created successfully", moh.getMohRegistrationNumber());
                } else {
                    log.warn("MOH {} already exists.", moh.getMohRegistrationNumber());
                }
            }

            // get Moh
            MOH moh = mohRepository.findByMohRegistrationNumber("M/W/1").orElseThrow(() -> new RuntimeException("MOH not found"));

            // Create and save Regions
            List<Region> regions = List.of(
                    new Region(1L, "R/W/1", "Udahamulla", 2000L, moh),
                    new Region(2L, "R/W/2", "Nugegoda", 2000L, moh),
                    new Region(3L, "R/W/3", "Navinna", 1000L, moh),
                    new Region(4L, "R/W/4", "Ambuldeniya", 2000L, moh),
                    new Region(5L, "R/W/5", "Delkanda", 1000L, moh)
            );

            for (Region region : regions) {
                if (regionRepository.findByRegionNumber(region.getRegionNumber()).isEmpty()) {
                    region.setCreatedBy(adminUser);
                    region.setUpdatedBy(adminUser);
                    region.setCreatedAt(LocalDateTime.now());
                    region.setUpdatedAt(LocalDateTime.now());
                    regionRepository.save(region);
                    log.info("Region {} created successfully", region.getRegionNumber());
                } else {
                    log.warn("Region {} already exists.", region.getRegionNumber());
                }
            }

            // get region
            Region region = regionRepository.findByRegionNumber("R/W/1").orElseThrow(() -> new RuntimeException("Region not found"));

            // get doctor and midwife
            User doctor = userRepository.findByEmail("doctor@maathru.com").orElseThrow(() -> new RuntimeException("Doctor not found"));
            User midwife = userRepository.findByEmail("midwife@maathru.com").orElseThrow(() -> new RuntimeException("Midwife not found"));

            // Create and save Employees
            List<Employee> employees = List.of(
                    new Employee(1L, "071234567", "200121503122", Gender.MALE, LocalDate.parse("2001-08-02", DateTimeFormatter.ISO_LOCAL_DATE), "No 48/16", "Wijerama Road", "Udahamulla", "System Admin", "Bsc in CS", adminUser,moh),
                    new Employee(2L, "072234567", "200121503123", Gender.MALE, LocalDate.parse("2001-08-03", DateTimeFormatter.ISO_LOCAL_DATE), "No 48/17", "Wijerama Road", "Udahamulla", "MOH Doctor", "MBbs", doctor,moh),
                    new Employee(3L, "074234567", "200121503124", Gender.FEMALE, LocalDate.parse("2001-08-04", DateTimeFormatter.ISO_LOCAL_DATE), "No 48/18", "Wijerama Road", "Udahamulla", "Midwife", "Dip in Zoology", midwife,moh)
            );

            for (Employee employee : employees) {
                if (employeeRepository.findByUserEmail(employee.getUser().getEmail()).isEmpty()) {
                    employee.setRegion(region);
                    employee.setCreatedBy(adminUser);
                    employee.setUpdatedBy(adminUser);
                    employee.setCreatedAt(LocalDateTime.now());
                    employee.setUpdatedAt(LocalDateTime.now());
                    employeeRepository.save(employee);
                    log.info("Employee {} created successfully", employee.getUser().getRole());
                } else {
                    log.warn("Employee {} already exists.", employee.getUser().getRole());
                }
            }

            // Get the normal user
            User regularUser = userRepository.findByEmail("user@maathru.com").orElseThrow(() -> new RuntimeException("Regular user not found"));

            // Create Blog Articles
            List<Blog> blogs = List.of(
                    new Blog(1L, "The Role of Maternal Health Clinics in Pregnancy", "<p>Pregnancy is a journey filled with excitement and challenges, requiring regular care and professional guidance to ensure the health of both the mother and baby. Maternal health clinics play a pivotal role in providing this care, offering comprehensive services that cover every stage of pregnancy. These clinics not only provide medical attention but also empower mothers with the knowledge needed for a safe and healthy pregnancy.</p><br></br><h4><b>The Importance of Maternal Health Clinics:</b></h4><p>Maternal health clinics serve as a cornerstone of prenatal care, addressing the unique health needs of expectant mothers. Regular visits to these clinics help monitor the baby’s growth, identify potential risks, and manage complications early. Services often include ultrasounds, routine health checks, and vital screenings to assess the mother’s and baby’s well-being. These clinics are instrumental in reducing maternal and infant mortality rates by providing timely medical interventions.</p><br></br><h4><b>Services Offered by Maternal Health Clinics:</b></h4><p>Maternal health clinics offer a wide range of services tailored to meet the needs of expectant mothers. These include:</p><ul><li><b>Prenatal Checkups:</b> Regular visits to monitor the health of the mother and the developing baby.</li><li><b>Ultrasound Scans:</b> Non-invasive imaging to assess fetal growth and detect abnormalities.</li><li><b>Health Screenings:</b> Blood pressure, blood sugar, and anemia tests to identify health issues early.</li><li><b>Nutrition Counseling:</b> Guidance on maintaining a healthy diet to support pregnancy.</li><li><b>Childbirth Education:</b> Preparing mothers for labor, delivery, and postpartum recovery.</li></ul><br></br><h4><b>Benefits of Regular Clinic Visits:</b></h4><p>Frequent visits to a maternal health clinic offer numerous benefits, including early detection of complications like gestational diabetes and preeclampsia, personalized guidance on managing pregnancy discomforts, and reassurance through regular monitoring. Clinics also provide emotional support, helping mothers cope with the mental and physical challenges of pregnancy.</p><br></br><h4><b>Tips for Choosing the Right Maternal Health Clinic:</b></h4><p>When selecting a maternal health clinic, consider factors such as the clinic’s proximity to your home, the availability of experienced healthcare providers, and the range of services offered. Look for clinics with a welcoming environment and staff who prioritize patient comfort and education. Reading reviews and seeking recommendations can also help in making an informed choice.</p><br></br><h4><b>Conclusion:</b></h4><p>Maternal health clinics are invaluable in supporting mothers throughout their pregnancy journey, ensuring safe and healthy outcomes for both mother and baby. By offering a blend of medical expertise and compassionate care, these clinics empower mothers to approach childbirth with confidence. Regular visits to a trusted clinic not only provide peace of mind but also lay the foundation for a healthy start to life for your baby.</p><br></br><br></br><h4><b>References:</b></h4><ul><li><a href=\"https://www.who.int/maternal-health\">https://www.who.int/maternal-health</a></li><li><a href=\"https://www.cdc.gov/ncbddd/pregnancy\">https://www.cdc.gov/ncbddd/pregnancy</a></li><li><a href=\"https://www.marchofdimes.org/pregnancy/your-pregnancy-care.aspx\">https://www.marchofdimes.org/pregnancy/your-pregnancy-care.aspx</a></li></ul>\n", "Maternal Health", "Ensure readability for a general audience. Verify all listed services align with current maternal health standards.", "http://localhost:8081/images/sampleArticle1.jpg", "", "APPROVED", "", "Prenatal Care, Pregnancy Health, Maternal Clinics"),
                    new Blog(2L, "The Importance of Postnatal Care for New Mothers", "<p>Postnatal care is as crucial as prenatal care, focusing on the recovery and well-being of new mothers after childbirth. This phase, often called the \"fourth trimester,\" involves significant physical and emotional adjustments. Proper postnatal care ensures the mother's recovery and supports her in adapting to the new responsibilities of motherhood.</p><br></br><h4><b>Why Postnatal Care Matters:</b></h4><p>The postpartum period is a time of healing and bonding. New mothers face challenges such as hormonal changes, breastfeeding issues, and fatigue. Postnatal care addresses these concerns by providing medical attention, emotional support, and education. It also helps detect and manage postpartum depression and other complications early.</p><br></br><h4><b>Key Components of Postnatal Care:</b></h4><p>Effective postnatal care encompasses:</p><ul><li><b>Medical Checkups:</b> Monitoring the mother’s recovery, including uterus healing and overall health.</li><li><b>Breastfeeding Support:</b> Guidance on proper latching, milk supply issues, and avoiding mastitis.</li><li><b>Mental Health Support:</b> Screening for postpartum depression and providing counseling.</li><li><b>Nutrition and Hydration:</b> Ensuring mothers consume a diet rich in nutrients to support healing and breastfeeding.</li><li><b>Physical Recovery:</b> Exercises to strengthen the pelvic floor and core muscles post-delivery.</li></ul><br></br><h4><b>Common Challenges and How Postnatal Care Helps:</b></h4><p>From sleep deprivation to physical discomfort, the postpartum period can be overwhelming. Postnatal care provides tailored solutions for challenges like sleep schedules, postpartum bleeding, and breastfeeding pain. It also empowers mothers with knowledge on baby care and recovery tips, building their confidence in this new phase of life.</p><br></br><h4><b>Tips for a Smooth Recovery:</b></h4><p>New mothers can ease their recovery by prioritizing rest, maintaining open communication with their healthcare providers, and seeking help from family and friends. Practicing self-care and joining postnatal support groups can also foster emotional well-being during this transformative phase.</p><br></br><h4><b>Conclusion:</b></h4><p>Postnatal care is essential for a mother’s physical and emotional recovery after childbirth. By focusing on health, nutrition, and support, new mothers can navigate this period confidently and bond deeply with their newborns. Regular checkups and open communication with healthcare providers ensure a healthy start to motherhood.</p><br></br><br></br><h4><b>References:</b></h4><ul><li><a href=\"https://www.who.int/health-topics/postnatal-care\">https://www.who.int/health-topics/postnatal-care</a></li><li><a href=\"https://www.acog.org/womens-health/faqs/postpartum-care\">https://www.acog.org/womens-health/faqs/postpartum-care</a></li><li><a href=\"https://www.marchofdimes.org/postpartum-care.aspx\">https://www.marchofdimes.org/postpartum-care.aspx</a></li></ul>\n", "Maternal Health", "Verify medical advice aligns with current postnatal guidelines. Ensure emotional support aspects are addressed.", "http://localhost:8081/images/sampleArticle2.jpg", "", "APPROVED", "", "Postnatal Health, Maternal Recovery, New Mother Care"),
                    new Blog(3L, "Essential Vaccines for Newborns and Infants", "<p>Vaccination is a cornerstone of newborn health, protecting infants from potentially life-threatening diseases during their early years. Ensuring that your child receives the recommended vaccines on time can safeguard their health and contribute to public immunity. This guide outlines the essential vaccines for newborns and their importance in early childhood.</p><br></br><h4><b>Why Vaccines Are Crucial:</b></h4><p>Vaccines help build immunity by exposing the body to weakened or inactive forms of pathogens. For newborns, whose immune systems are still developing, vaccines provide a protective barrier against diseases like measles, polio, and whooping cough. They also prevent the spread of these illnesses in the community.</p><br></br><h4><b>Key Vaccines for Newborns:</b></h4><p>Newborns typically receive the following vaccines as part of their immunization schedule:</p><ul><li><b>Hepatitis B Vaccine:</b> Protects against liver infection caused by the hepatitis B virus, usually administered at birth.</li><li><b>BCG Vaccine:</b> Prevents severe forms of tuberculosis, commonly given in areas with high TB prevalence.</li><li><b>Rotavirus Vaccine:</b> Shields against rotavirus infections that cause severe diarrhea and dehydration.</li><li><b>DTP Vaccine:</b> Covers diphtheria, tetanus, and pertussis (whooping cough), crucial for respiratory and muscular health.</li><li><b>Polio Vaccine:</b> Protects against poliomyelitis, a disease that can cause paralysis.</li></ul><br></br><h4><b>Tips for Parents:</b></h4><p>Parents can ensure timely immunization by maintaining a vaccination record and consulting with their pediatrician about any missed doses. It's also vital to understand the common side effects, such as mild fever or swelling, which indicate the vaccine is working. Keeping babies comfortable after vaccinations and reporting severe reactions to healthcare providers is equally important.</p><br></br><h4><b>Addressing Vaccine Hesitancy:</b></h4><p>Some parents may have concerns about vaccine safety or efficacy. It’s important to rely on evidence-based information from trusted sources, like WHO and CDC. Discussing these concerns with healthcare providers can help parents make informed decisions about their child’s health.</p><br></br><h4><b>Conclusion:</b></h4><p>Vaccines are essential for protecting newborns against life-threatening diseases and ensuring their healthy development. By adhering to the recommended immunization schedule, parents can provide their children with the best start in life. Consult your pediatrician to stay updated on vaccination timelines and address any questions you may have.</p><br></br><br></br><h4><b>References:</b></h4><ul><li><a href=\"https://www.who.int/vaccines\">https://www.who.int/vaccines</a></li><li><a href=\"https://www.cdc.gov/vaccines/schedules\">https://www.cdc.gov/vaccines/schedules</a></li><li><a href=\"https://www.unicef.org/immunization\">https://www.unicef.org/immunization</a></li></ul>\n", "Infant Health", "Cross-check the vaccine schedule against WHO and CDC recommendations. Simplify any medical jargon for easy understanding.", "http://localhost:8081/images/sampleArticle3.jpg", "", "APPROVED", "", "Vaccination, Newborn Health, Immunization Schedule"),
                    new Blog(4L, "Understanding Growth Milestones in Early Childhood", "<p>Early childhood is a period of rapid growth and development, filled with numerous milestones that mark a child’s journey toward independence. Understanding these growth milestones helps parents support their child’s physical, emotional, and cognitive development. By monitoring progress and addressing potential concerns early, parents can ensure their child thrives during these critical years.</p><br></br><h4><b>What Are Growth Milestones?</b></h4><p>Growth milestones are specific skills or behaviors that most children achieve within a certain age range. These milestones serve as guidelines to track development across various domains, including physical growth, motor skills, language acquisition, and social interaction. While every child develops at their own pace, significant delays in meeting milestones may indicate the need for professional evaluation.</p><br></br><h4><b>Key Developmental Stages:</b></h4><p>Children progress through several key stages during early childhood:</p><ul><li><b>Infancy (0-12 months):</b> Rapid growth in weight and height, development of motor skills like rolling over and sitting up, and early communication through cooing and babbling.</li><li><b>Toddlerhood (1-3 years):</b> Improved mobility with walking and running, development of fine motor skills, and the emergence of simple sentences.</li><li><b>Preschool Age (3-5 years):</b> Refinement of motor coordination, expansion of vocabulary, and growth in social and imaginative play.</li></ul><br></br><h4><b>Factors Influencing Growth:</b></h4><p>Several factors impact a child’s growth and development, including genetics, nutrition, environment, and emotional support. Providing a nurturing environment with balanced nutrition, adequate sleep, and opportunities for physical activity fosters healthy development. Parents should also engage in regular, age-appropriate activities to stimulate cognitive and emotional growth.</p><br></br><h4><b>How to Monitor Milestones:</b></h4><p>Parents can track their child’s progress by observing their behavior, engaging in interactive play, and attending routine pediatric checkups. Growth charts and developmental questionnaires provided by healthcare professionals offer valuable insights into whether a child is meeting expected milestones.</p><br></br><h4><b>When to Seek Help:</b></h4><p>If a child shows significant delays in areas like speech, mobility, or social interaction, parents should consult a pediatrician or developmental specialist. Early intervention programs can address concerns effectively, ensuring children receive the support they need to thrive.</p><br></br><h4><b>Conclusion:</b></h4><p>Understanding growth milestones empowers parents to support their child’s journey through early childhood. By fostering a positive, nurturing environment and staying attuned to their child’s needs, parents can promote healthy development and address potential concerns early. Regular checkups with healthcare providers ensure a comprehensive approach to tracking and supporting growth milestones.</p><br></br><br></br><h4><b>References:</b></h4><ul><li><a href=\"https://www.cdc.gov/ncbddd/childdevelopment\">https://www.cdc.gov/ncbddd/childdevelopment</a></li><li><a href=\"https://www.healthychildren.org/English/ages-stages\">https://www.healthychildren.org/English/ages-stages</a></li><li><a href=\"https://www.who.int/child-growth-standards\">https://www.who.int/child-growth-standards</a></li></ul>\n", "Child Growth", "Ensure milestones are accurate and reflect current pediatric recommendations. Verify references for credibility.", "http://localhost:8081/images/sampleArticle4.jpg", "", "APPROVED", "", "Childhood Development, Growth Milestones, Parenting Tips"),
                    new Blog(5L, "How to Create a Balanced Diet Plan for Toddlers", "<p>Early childhood is a period of rapid growth and development, filled with numerous milestones that mark a child’s journey toward independence. Understanding these growth milestones helps parents support their child’s physical, emotional, and cognitive development. By monitoring progress and addressing potential concerns early, parents can ensure their child thrives during these critical years.</p><br></br><h4><b>What Are Growth Milestones?</b></h4><p>Growth milestones are specific skills or behaviors that most children achieve within a certain age range. These milestones serve as guidelines to track development across various domains, including physical growth, motor skills, language acquisition, and social interaction. While every child develops at their own pace, significant delays in meeting milestones may indicate the need for professional evaluation.</p><br></br><h4><b>Key Developmental Stages:</b></h4><p>Children progress through several key stages during early childhood:</p><ul><li><b>Infancy (0-12 months):</b> Rapid growth in weight and height, development of motor skills like rolling over and sitting up, and early communication through cooing and babbling.</li><li><b>Toddlerhood (1-3 years):</b> Improved mobility with walking and running, development of fine motor skills, and the emergence of simple sentences.</li><li><b>Preschool Age (3-5 years):</b> Refinement of motor coordination, expansion of vocabulary, and growth in social and imaginative play.</li></ul><br></br><h4><b>Factors Influencing Growth:</b></h4><p>Several factors impact a child’s growth and development, including genetics, nutrition, environment, and emotional support. Providing a nurturing environment with balanced nutrition, adequate sleep, and opportunities for physical activity fosters healthy development. Parents should also engage in regular, age-appropriate activities to stimulate cognitive and emotional growth.</p><br></br><h4><b>How to Monitor Milestones:</b></h4><p>Parents can track their child’s progress by observing their behavior, engaging in interactive play, and attending routine pediatric checkups. Growth charts and developmental questionnaires provided by healthcare professionals offer valuable insights into whether a child is meeting expected milestones.</p><br></br><h4><b>When to Seek Help:</b></h4><p>If a child shows significant delays in areas like speech, mobility, or social interaction, parents should consult a pediatrician or developmental specialist. Early intervention programs can address concerns effectively, ensuring children receive the support they need to thrive.</p><br></br><h4><b>Conclusion:</b></h4><p>Understanding growth milestones empowers parents to support their child’s journey through early childhood. By fostering a positive, nurturing environment and staying attuned to their child’s needs, parents can promote healthy development and address potential concerns early. Regular checkups with healthcare providers ensure a comprehensive approach to tracking and supporting growth milestones.</p><br></br><br></br><h4><b>References:</b></h4><ul><li><a href=\"https://www.cdc.gov/ncbddd/childdevelopment\">https://www.cdc.gov/ncbddd/childdevelopment</a></li><li><a href=\"https://www.healthychildren.org/English/ages-stages\">https://www.healthychildren.org/English/ages-stages</a></li><li><a href=\"https://www.who.int/child-growth-standards\">https://www.who.int/child-growth-standards</a></li></ul>\n", "Child Growth", "Ensure milestones are accurate and reflect current pediatric recommendations. Verify references for credibility.", "http://localhost:8081/images/sampleArticle5.jpg", "", "APPROVED", "", "Childhood Development, Growth Milestones, Parenting Tips"),
                    new Blog(6L, "The Role of Hydration During Pregnancy", "<p>Proper hydration is one of the most critical yet often overlooked aspects of a healthy pregnancy. Water plays a vital role in supporting both the mother and baby, ensuring optimal physiological functioning and aiding in fetal development. Staying well-hydrated throughout pregnancy can help reduce common discomforts and promote overall wellness.</p><br></br><h4><b>Why Hydration is Important During Pregnancy:</b></h4><p>Water is essential for forming amniotic fluid, facilitating nutrient transport, and maintaining proper circulation. During pregnancy, the body’s blood volume increases significantly to support the growing baby, making hydration even more important. Adequate water intake helps regulate body temperature, prevent dehydration, and reduce pregnancy-related symptoms like swelling and constipation.</p><br></br><h4><b>How Much Water Should You Drink?</b></h4><p>Experts recommend pregnant women drink about 8-12 cups (64-96 ounces) of water daily, though individual needs may vary based on activity levels, climate, and overall health. Thirst is a natural indicator, but it’s best to maintain consistent hydration throughout the day to avoid dehydration.</p><br></br><h4><b>Benefits of Staying Hydrated During Pregnancy:</b></h4><ul><li><b>Prevents Dehydration:</b> Dehydration can lead to headaches, fatigue, and dizziness. Severe cases may increase the risk of preterm labor.</li><li><b>Supports Amniotic Fluid Levels:</b> Adequate hydration helps maintain the amniotic sac, protecting and cushioning the baby.</li><li><b>Improves Digestion:</b> Water aids in preventing constipation, a common pregnancy complaint.</li><li><b>Regulates Body Temperature:</b> Staying hydrated helps the body cool down efficiently, reducing the risk of overheating.</li><li><b>Promotes Healthy Skin:</b> Proper hydration can minimize skin dryness and itchiness often experienced during pregnancy.</li></ul><br></br><h4><b>Tips for Staying Hydrated:</b></h4><p>Maintaining hydration doesn’t have to be boring. Here are some practical tips:</p><ul><li>Carry a reusable water bottle and sip throughout the day.</li><li>Incorporate hydrating foods like cucumbers, watermelon, and oranges into your diet.</li><li>Add natural flavor to water using slices of lemon, lime, or mint leaves.</li><li>Drink herbal teas or diluted fruit juices for variety.</li><li>Limit caffeine intake, as it can contribute to dehydration.</li></ul><br></br><h4><b>Signs of Dehydration:</b></h4><p>Knowing the symptoms of dehydration can help prevent complications. Look out for signs such as dark yellow urine, dry mouth, dizziness, or fatigue. Severe cases may require immediate medical attention.</p><br></br><h4><b>Conclusion:</b></h4><p>Staying hydrated during pregnancy is essential for the health and well-being of both the mother and baby. By prioritizing water intake and incorporating hydrating foods and beverages, expectant mothers can enjoy a smoother pregnancy experience while reducing the risk of complications. Always consult a healthcare provider to tailor hydration recommendations to your individual needs.</p><br></br><br></br><h4><b>References:</b></h4><ul><li><a href=\"https://www.acog.org/womens-health/faqs/nutrition-during-pregnancy\">https://www.acog.org/womens-health/faqs/nutrition-during-pregnancy</a></li><li><a href=\"https://www.cdc.gov/nutrition/maternal-infant-nutrition/hydration.html\">https://www.cdc.gov/nutrition/maternal-infant-nutrition/hydration.html</a></li><li><a href=\"https://www.nhs.uk/pregnancy/keeping-well/foods-to-avoid/\">https://www.nhs.uk/pregnancy/keeping-well/foods-to-avoid/</a></li></ul>\n", "Maternal Health", "Ensure hydration recommendations align with expert guidelines. Mention the benefits of water intake and alternatives like hydrating foods or beverages.", "http://localhost:8081/images/sampleArticle6.jpg", "", "APPROVED", "", "Pregnancy, Hydration, Maternal Wellness")
                    );

            for (Blog blog : blogs) {
                if (blogRepository.findByTitle(blog.getTitle()).isEmpty()) {
                    blog.setCreatedAt(LocalDateTime.now());
                    blog.setUpdatedAt(LocalDateTime.now());
                    blog.setCreatedBy(regularUser);
                    blog.setUpdatedBy(regularUser);
                    blogRepository.save(blog);
                    log.info("Blog {} created successfully", blog.getBlogId());
                } else {
                    log.warn("Blog {} already exists", blog.getBlogId());
                }
            }
        };
    }
}