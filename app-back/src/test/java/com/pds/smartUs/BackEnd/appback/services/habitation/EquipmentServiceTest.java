package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Equipment;
import com.pds.smartUs.BackEnd.appback.entities.EquipmentConsomption;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.DeviceRepository;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.EquipmentConsomptionRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {

    @InjectMocks
    private EquipmentService equipmentServiceUderTest;
    @Mock
    private  EquipmentConsomptionRepository equipmentConsomptionRepository;
    @Mock
    private  DeviceRepository deviceRepository;


    private static final Logger logger = Logger.getLogger(HabitationServiceTest.class.getName());
    private static Instant startChrono;

    private AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        equipmentServiceUderTest=new EquipmentService(equipmentConsomptionRepository,deviceRepository);
    }


    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @BeforeAll
    public static void initChrono() {
        logger.info("Begin of all tests for Equipment Service");
        startChrono = Instant.now();
    }

    @AfterAll
    public static void durationTest() {
        logger.info("End of all tests for Equipment Service");
        Instant endChrono = Instant.now();
        long duration = Duration.between(startChrono, endChrono).toMillis();
        logger.info("duration of tests for Equipment Service : " + duration + "ms");
    }

    @Test
    @DisplayName("Should return the last 10 consumptions for a given Equipment")
    void getEquipmentConsomptionByEquipmentId() {
        //given
        Long EquipmentId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        EquipmentConsomption cosno1 = EquipmentConsomption.builder().consomption(10).build();
        EquipmentConsomption cosno2 = EquipmentConsomption.builder().consomption(11).build();
        List<EquipmentConsomption> result = new ArrayList<>();
        result.add(cosno1);
        result.add(cosno2);

        when(equipmentConsomptionRepository.findEquipmentConsomptionByEquipmentIdOrderByTimeDesc(EquipmentId,pageable)).thenReturn(result);
        //Then

        List <EquipmentConsomption> resulttest = equipmentServiceUderTest.getEquipmentConsomptionByEquipmentId(EquipmentId);
        assertTrue((resulttest.size() == 2 ) == (result.size()==2)) ;
        assertTrue(resulttest.get(0).getConsomption()==10) ;
    }

    @Test
    @DisplayName("condition 1 : Should return the last 10 consumptions for a given Equipment" +
            "should respect the given limit and don't take the 4th cumsumption for each equipment" +
            "should have the name of the equipement as key")

    void getEquipmentsConsumptionByRoomId() {
        // given
        Long roomId = 1L;
        int limit = 3;
        Equipment eq= Equipment.builder().id(1L).name("Lampe").build();
        Equipment eq2= Equipment.builder().id(2L).name("Four").build();

        EquipmentConsomption conso1= EquipmentConsomption.builder().id(1L).equipment(eq).consomption(10).build();
        EquipmentConsomption conso2= EquipmentConsomption.builder().id(2L).equipment(eq).consomption(11).build();
        EquipmentConsomption conso3= EquipmentConsomption.builder().id(3L).equipment(eq).consomption(12).build();
        EquipmentConsomption conso4= EquipmentConsomption.builder().id(4L).equipment(eq).consomption(13).build();
        EquipmentConsomption conso11= EquipmentConsomption.builder().id(5L).equipment(eq2).consomption(100).build();
        EquipmentConsomption conso22= EquipmentConsomption.builder().id(6L).equipment(eq2).consomption(120).build();
        EquipmentConsomption conso33= EquipmentConsomption.builder().id(7L).equipment(eq2).consomption(121).build();
        EquipmentConsomption conso44= EquipmentConsomption.builder().id(8L).equipment(eq2).consomption(123).build();

        List<EquipmentConsomption> equipmentsConsumtions = Arrays.asList(conso1,conso2,conso3,conso4,conso11,conso22,conso33,conso44);

        //when
            when(equipmentConsomptionRepository.findAllByEquipmentHouseroomIdRoom(roomId)).thenReturn(equipmentsConsumtions);
        //then

        Map<String, List<EquipmentConsomption>> result = equipmentServiceUderTest.getEquipmentsConsumptionByRoomId(roomId,limit);

        // check condition 1
        assertTrue(result.size()==2);

        // check condition 2
        assertTrue(result.get("Lampe").size()==3);

        //check condition 3:
        int i=1;
        for (Map.Entry<String, List<EquipmentConsomption>>pair : result.entrySet()) {
            if (i==1){
                assertTrue(pair.getKey()=="Lampe");
            }else {
                assertTrue(pair.getKey()=="Four");
            }
            i++;

        }


    }

    @Test
    @DisplayName("Should return a consumption value for numbers of days chosen -1 (because we use beetwen in the deo layer")
    void getConsumptionBydate() {

        //given
        Long habitationid = 1L;
        //numbers of days chosen =4
        LocalDate a[] = new LocalDate[]{LocalDate.of(2022,05,05), LocalDate.of(2022,05,04), LocalDate.of(2022,05,03), LocalDate.of(2022,05,02)};
        ArrayList<LocalDate> dates=new ArrayList<>(Arrays.asList(a));
        //when
        // When
        for (int i = 0; i < dates.size()-1; i++) {
            when(equipmentConsomptionRepository.findConsumptionByDate(habitationid,dates.get(i+1),dates.get(i))).thenReturn(1.0+i);
        }

        //then
        List<Double> result= equipmentServiceUderTest.getConsumptionBydate(habitationid,dates);
        assertTrue(result.size() == 3);
        assertTrue(result.get(1).equals(1.0+1));

    }

    @Test
    void getHouseEquipment() {
    }
}