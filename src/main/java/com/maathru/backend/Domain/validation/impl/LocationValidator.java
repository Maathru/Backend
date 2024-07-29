package com.maathru.backend.Domain.validation.impl;

import com.maathru.backend.enumeration.District;
import com.maathru.backend.enumeration.Province;
import com.maathru.backend.enumeration.Area;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.maathru.backend.enumeration.District.*;
import static com.maathru.backend.enumeration.Province.*;

@Slf4j
public class LocationValidator {
    private static final Map<District, Set<Area>> districtToRegions = new HashMap<>();
    private static final Map<Province, Set<District>> provinceToDistricts = new HashMap<>();

    static {
        // Populate province to district mapping
        provinceToDistricts.put(NORTHERN, Set.of(
                JAFFNA,
                KILINOCHCHI,
                MANNAR,
                MULLATIVU,
                VAVUNIYA));
        provinceToDistricts.put(NORTH_CENTRAL, Set.of(
                ANURADHAPURA,
                POLONNARUWA));
        provinceToDistricts.put(NORTH_WESTERN, Set.of(
                KURUNEGALA,
                PUTTALAMA));
        provinceToDistricts.put(CENTRAL, Set.of(
                KANDY,
                MATALE,
                NUWARA_ELIYA));
        provinceToDistricts.put(EASTERN, Set.of(
                TRINCOMALEE,
                AMPARA,
                BATTICALO));
        provinceToDistricts.put(WESTERN, Set.of(
                COLOMBO,
                GAMPAHA,
                KALUTHARA));
        provinceToDistricts.put(UVA, Set.of(
                BADULLA,
                MONARAGALA));
        provinceToDistricts.put(SABARAGAMUWA, Set.of(
                RATNAPURA,
                MONARAGALA));
        provinceToDistricts.put(SOUTHERN, Set.of(
                GALLE,
                MATARA,
                HAMBANTHOTA));

        // Populate district to region mapping
        districtToRegions.put(AMPARA, Set.of(
                Area.AMPARA,
                Area.DAMANA,
                Area.LAHUGALA,
                Area.MAHAOYA,
                Area.PADIYATHALAWA,
                Area.UHANA));
        districtToRegions.put(ANURADHAPURA, Set.of(
                Area.GALINBIDINUWEWA,
                Area.GALNEWA,
                Area.HOROWPOTHANA,
                Area.IPALOGAMA,
                Area.KAHATAGASDIGILILIYA,
                Area.KEBITHIGOLLEWA,
                Area.KEKIRAWA,
                Area.MADAWACHCHIYA,
                Area.MIHINTALE,
                Area.NOCHCHIYAGAMA,
                Area.NUWARAGAM_PALATHA_CENTRAL,
                Area.NUWARAGAM_PALATHA_EAST,
                Area.PADAVIYA,
                Area.PALAGALA,
                Area.RAJANGANAYA,
                Area.RAMBEWA,
                Area.THABUTHTHEGAMA,
                Area.THALAWA,
                Area.THIRAPPANE));
        districtToRegions.put(BADULLA, Set.of(
                Area.BADULLA,
                Area.BANDARAWELA,
                Area.ELLA,
                Area.GIRANDURUKOTTE,
                Area.HALI_ELA,
                Area.HAPUTALE,
                Area.KANDAKETIYA,
                Area.MAHIYANGANA,
                Area.PARANAGAMA,
                Area.PASSARA,
                Area.WELIMADA));
        districtToRegions.put(BATTICALO, Set.of(
                Area.ARAYAMPATHY,
                Area.BATTICALO,
                Area.CHENKALADY,
                Area.ERAVUR,
                Area.KALUWANCHIKUDY,
                Area.KATTANKUDY,
                Area.ODAMAVADI,
                Area.PADDIPPALAI,
                Area.VAKARAI,
                Area.VALAICHCHENAI,
                Area.VAVUNATHEVU,
                Area.VELLAVELY));
        districtToRegions.put(COLOMBO, Set.of(
                Area.BORALESGAMUWA,
                Area.DEHIWALA,
                Area.HANWELLA,
                Area.HOMAGAMA,
                Area.KADUWELA,
                Area.KOLONNAWA,
                Area.MAHARAGAMA,
                Area.MORATUWA,
                Area.NUGEGODA,
                Area.PADUKKA,
                Area.PILIYANDALA,
                Area.PITTAKOTTE));
        districtToRegions.put(GALLE, Set.of(
                Area.AMBALANGODA,
                Area.AKMEEMANA,
                Area.BADDEGAMA,
                Area.BALAPITIYA,
                Area.AHUNGALLE,
                Area.BOPE_PODDALA,
                Area.ELPITIYA,
                Area.GALLE_MC,
                Area.GONAPEENUWALA,
                Area.HABARADUWA,
                Area.HIKKADUWA,
                Area.INDURUWA,
                Area.IMADUWA,
                Area.KARANDENIYA,
                Area.NELUWA,
                Area.NIYAGAMA,
                Area.THAWALAMA,
                Area.UDUGAMA,
                Area.WELIVITIYA,
                Area.YAKKALAMULLA));
        districtToRegions.put(GAMPAHA, Set.of(
                Area.ATTANAGALLA,
                Area.BIYAGAMA,
                Area.BOI_KATUNAYAKA,
                Area.DIVULAPITIYA,
                Area.DOMPE,
                Area.GAMPAHA,
                Area.JAELA,
                Area.KATANA,
                Area.KELANIYA,
                Area.MAHARA,
                Area.MINUWANGODA,
                Area.MIRIGAMA,
                Area.NEGOMBO,
                Area.RAGAMA,
                Area.SEEDUWA,
                Area.WATTALA));
        districtToRegions.put(HAMBANTHOTA, Set.of(
                Area.AMBALANTHOTA,
                Area.ANGUNAKOLAPALESSA,
                Area.BELIATTA,
                Area.HAMBANTOTA,
                Area.KATUWANA,
                Area.LUNUGAMVEHERA,
                Area.OKEWELA,
                Area.SOORIYAWEWA,
                Area.TANGALLE,
                Area.TISSAMAHARAMA,
                Area.WALASMULLA,
                Area.WEERAKATIYA));
        districtToRegions.put(JAFFNA, Set.of(
                Area.CHANKANAI,
                Area.CHAWACACHCHERI,
                Area.JAFFNA,
                Area.KARAVEDDY,
                Area.KAYTS,
                Area.KOPAY,
                Area.NALLUR,
                Area.POINT_PEDRO,
                Area.SANDILIPAY,
                Area.UDUVIL));
        districtToRegions.put(KANDY, Set.of(
                Area.AKURANA,
                Area.BAMBARADENIYA,
                Area.DOLUWA,
                Area.GALAGEDARA,
                Area.GALAH,
                Area.GANGA_IHALA_KORALE,
                Area.GANGAWATAKORALE,
                Area.TENNEKUMBURA,
                Area.HARISPATTUWA,
                Area.HASALAKA,
                Area.HATHARALIYADDA,
                Area.KADUGANNAWA,
                Area.KUNDASALE,
                Area.MANIKHINNA,
                Area.MEDADUMBARA,
                Area.NAWALAPITIYA,
                Area.PANVILA,
                Area.THALATUOYA,
                Area.POOJAPITIYA,
                Area.UDADUMBARA,
                Area.UDAPALATHA_GAMPALA,
                Area.UDUNUWARA,
                Area.WATHTHEGAMA,
                Area.YATINUWARA));
        districtToRegions.put(KALUTHARA, Set.of(
                Area.AGALAWATTA,
                Area.BANDARAGAMA,
                Area.BULATHSINHALA,
                Area.HORANA,
                Area.INGIRIYA,
                Area.KALUTHARA,
                Area.MADURAWALA,
                Area.MATHUGAMA,
                Area.PALINDANUWARA,
                Area.PANADURA,
                Area.WALALLAWITA));
        districtToRegions.put(KEGALLE, Set.of(
                Area.ARANAYAKE,
                Area.BULATHKOHUPITIYA,
                Area.DEHIOVITA,
                Area.DERANIYAGALA,
                Area.GALIGAMUWA,
                Area.KEGALLE,
                Area.MAWANELLA,
                Area.RAMBUKKANA,
                Area.RUWANWELLA,
                Area.WARAKAPOLA,
                Area.YATIYANTOTA));
        districtToRegions.put(KILINOCHCHI, Set.of(
                Area.KILINOCHCHI,
                Area.PALLAI,
                Area.POONAKARY));
        districtToRegions.put(KURUNEGALA, Set.of(
                Area.ALAUWA,
                Area.BINGIRIYA,
                Area.GALGAMUWA,
                Area.GANEWATTA,
                Area.GIRIBAWA,
                Area.IBBAGAMUWA,
                Area.KATUPOTHA,
                Area.KOTAWEHERA,
                Area.KULIYAPITIYA_WEST,
                Area.KURUNEGALA,
                Area.MAHO,
                Area.MAWATHAGAMA,
                Area.NARAMMALA,
                Area.NIKAWERATIYA,
                Area.PANDUWASNUWARA,
                Area.PANNALA,
                Area.POLGAHAWELA,
                Area.POLPITIGAMA,
                Area.RIDEEGAMA,
                Area.UDUBADDAWA,
                Area.WARIYAPOLA));
        districtToRegions.put(MATALE, Set.of(
                Area.ABANGANGA,
                Area.DAMBULLA,
                Area.GALEWELA,
                Area.LAGGALA,
                Area.PALLEGAMA,
                Area.MATALE,
                Area.NAULA,
                Area.PALLEPOLA,
                Area.UKUWELA,
                Area.WILGAMUWA,
                Area.YATAWATTA));
        districtToRegions.put(MONARAGALA, Set.of(
                Area.BADULKUMBURA,
                Area.BIBILE,
                Area.BUTTALA,
                Area.KATHARAGAMA,
                Area.MADAGAMA,
                Area.MADULLA,
                Area.MONARAGALA,
                Area.SEWANAGALA,
                Area.SIYAMBALANDUWA,
                Area.THANAMALWILA,
                Area.WELLAWAYA));
        districtToRegions.put(MANNAR, Set.of(
                Area.ADAMPAN,
                Area.MADHU,
                Area.MANNAR,
                Area.MURUNKAN,
                Area.MUSALI));
        districtToRegions.put(MATARA, Set.of(
                Area.AKURESSA,
                Area.ATHURALIYA,
                Area.DEVINUARA,
                Area.DICKWELLA,
                Area.HAKMANA,
                Area.KAMBURUPITIYA,
                Area.KIRINDA,
                Area.PUHULWELLA,
                Area.KOTAPOLA,
                Area.MALIMBADA,
                Area.MATARA,
                Area.MORAWAKA,
                Area.MULATIYANA,
                Area.PASGODA,
                Area.THIHAGODA,
                Area.WELIGAMA,
                Area.WELINITIYA));
        districtToRegions.put(MULLATIVU, Set.of(
                Area.MALLAVI,
                Area.MULLAITIVU,
                Area.ODDUSUDDAN,
                Area.PUTHUKUDYIRUPU));
        districtToRegions.put(NUWARA_ELIYA, Set.of(
                Area.AMBAGAMUWA,
                Area.HANGURANKETHA,
                Area.KOTAGALA,
                Area.KOTHMALE,
                Area.MASKELIYA,
                Area.NAWATHISPANE,
                Area.NUWARA_ELIYA,
                Area.RIKILLAGASKADA,
                Area.THALAWAKELA,
                Area.LINDULA,
                Area.WALAPANE));
        districtToRegions.put(POLONNARUWA, Set.of(
                Area.DIMBULAGALA,
                Area.ELEHARA,
                Area.HINGURAKGODA,
                Area.LANKAPURA,
                Area.MEDIRIGIRIYA,
                Area.THAMANKADUWA,
                Area.WALIKANDA));
        districtToRegions.put(PUTTALAMA, Set.of(
                Area.ANAMADUWA,
                Area.ARACHCHIKATTUWA,
                Area.CHILAW,
                Area.DANKOTUWA,
                Area.KALPITIYA,
                Area.KARUWALAGASWEWA,
                Area.MARAWILA,
                Area.MUNDAL,
                Area.PUTTALAM));
        districtToRegions.put(RATNAPURA, Set.of(
                Area.AYAGAMA,
                Area.BALANGODA,
                Area.EHELIYAGODA,
                Area.ELAPATHA,
                Area.KIRIELLA,
                Area.KOLONNA,
                Area.KURUWITA,
                Area.NIVITHIGALA));
        districtToRegions.put(TRINCOMALEE, Set.of(
                Area.GOMARANGADAWELA,
                Area.ICHHILAMPATHAI,
                Area.KANTHALI,
                Area.KINNIYA,
                Area.KUCHCHAVELI,
                Area.MUTHUR,
                Area.PADAVI_SRIPURA,
                Area.SERUWILA,
                Area.THAMPALAGAMAM,
                Area.TRINCOMALEE));
        districtToRegions.put(VAVUNIYA, Set.of(
                Area.VAVUNIYA,
                Area.CHEDDIKULAM,
                Area.VAVUNIYA_NORTH,
                Area.VAVUNIYA_SOUTH));
    }

    public static boolean isValidRegion(String provinceStr, String districtStr, String areaStr) {
        Province province;
        District district;
        Area area;

        try {
            province = Province.valueOf(provinceStr.toUpperCase());
            district = District.valueOf(districtStr.toUpperCase());
            area = Area.valueOf(areaStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Invalid enum value
            log.info("Invalid enum value");
            return false;
        }

        Set<District> districts = provinceToDistricts.get(province);
        if (districts == null || !districts.contains(district)) {
            return false;
        }

        Set<Area> regions = districtToRegions.get(district);
        return regions != null && regions.contains(area);
    }
}
