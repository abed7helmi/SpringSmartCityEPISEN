package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Habitation;
import com.pds.smartUs.BackEnd.appback.entities.Inhabitant;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.HabitationRepository;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.InhabitantRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class HabitationServiceTest {

    private static final Logger logger = Logger.getLogger(HabitationServiceTest.class.getName());


    @Mock
    private  HabitationRepository habitationRepository;
    @Mock
    private  InhabitantRepository inhabitantRepository;



    //    Services
    @InjectMocks
    private HabitationService habitationServiceUndertest;

    @InjectMocks
    private  HouseRoomService houseRoomService;
    @InjectMocks
    private  EquipmentService equipmentService;

    @InjectMocks
    private  ProductionService productionService;

    @InjectMocks
    private  BeposService beposService;


    private AutoCloseable autoCloseable;
    private static Instant startChrono;

    @BeforeEach
    void setUp() {
       autoCloseable = MockitoAnnotations.openMocks(this);

        habitationServiceUndertest = new HabitationService(habitationRepository,inhabitantRepository,houseRoomService,equipmentService,productionService,beposService);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @BeforeAll
    public static void initChrono() {
        logger.info("Begin of all tests for Habitation Service");
        startChrono = Instant.now();
    }

    @AfterAll
    public static void durationTest() {
        logger.info("End of all tests for Habitation Service");
        Instant endChrono = Instant.now();
        long duration = Duration.between(startChrono, endChrono).toMillis();
        logger.info("duration of tests for Habitation Service : " + duration + "ms");
    }








    // TESTS
    @Test
    @Disabled
    void gethabitation() {


    }

    @Test
    @Disabled
    void getInhabitant() {

    }

    @Test
    @Disabled
    void getComparisonByHabitationId() {
    }

    @Test
    @Disabled
    void getHabitationResumByHabitationId() {
    }



    @Test
    @DisplayName("Should return habitation by a given Inhabitant")
    void getHabitationByInhabitantId() {
        // given
        Long idinhabitant = 1L;
        // deo pretected result
        Inhabitant inhabitant = Inhabitant.builder().idInhabitant(idinhabitant).build();
        Habitation habitation= Habitation.builder().idHabitation(1L).inhabitant(inhabitant).zipCode(94100).build();

        // when
        when(habitationRepository.findByIdInhabitant(idinhabitant)).thenReturn(habitation);

        // then
        Habitation habitationtest =habitationServiceUndertest.getHabitationByInhabitantId(idinhabitant);
        assertEquals(habitation,habitationtest);
        assertTrue(habitationtest.getZipCode()==94100);
    }

    @Test
    @Disabled
    void getHabitationByInhabitantId2() {
    }
}