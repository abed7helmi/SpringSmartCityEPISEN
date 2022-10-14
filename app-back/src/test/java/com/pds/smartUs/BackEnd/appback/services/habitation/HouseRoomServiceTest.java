package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Habitation;
import com.pds.smartUs.BackEnd.appback.entities.HouseRoom;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.HouseRoomRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HouseRoomServiceTest {

    @InjectMocks
    private HouseRoomService houseRoomServiceUnderTest;
    @Mock
    private HouseRoomRepository houseRoomRepository;
    @Mock
    private EquipmentService equipmentService;

    private AutoCloseable autoCloseable;

    private static final Logger logger = Logger.getLogger(HabitationServiceTest.class.getName());

    private static Instant startChrono;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        houseRoomServiceUnderTest = new HouseRoomService(houseRoomRepository,equipmentService);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @BeforeAll
    public static void initChrono() {
        logger.info("Begin of all tests for HouseRoom Service");
        startChrono = Instant.now();
    }

    @AfterAll
    public static void durationTest() {
        logger.info("End of all tests for HouseRoom Service");
        Instant endChrono = Instant.now();
        long duration = Duration.between(startChrono, endChrono).toMillis();
        logger.info("duration of tests for HouseRoom Service : " + duration + "ms");
    }



    @Test
    @DisplayName("Should return all rooms for a given habitation, 3 for this test")
    void getAllByHabitationIdHabitation() {
        //given
        Long habitationId = 1L;
        // deo pretected result
        Habitation habitation = Habitation.builder().idHabitation(1L).build();
        HouseRoom room1 = HouseRoom.builder().idRoom(1L).roomName("totoRoom").habitation(habitation).build();
        HouseRoom room2 = HouseRoom.builder().idRoom(2L).roomName("titiRomom").habitation(habitation).build();
        HouseRoom room3 = HouseRoom.builder().idRoom(3L).roomName("tataRoom").habitation(habitation).build();

        List result = Arrays.asList(room1,room2,room3);

        //when
        when(houseRoomRepository.findAllByHabitationIdHabitation(habitationId)).thenReturn(result);

        //then
        List<HouseRoom> resulttest = houseRoomServiceUnderTest.getAllByHabitationIdHabitation(habitationId);

        assertEquals(result,resulttest);
        assertTrue(resulttest.size()==3);
        assertTrue(resulttest.get(0).getRoomName()=="totoRoom");
    }

    @Test
    @Disabled
    void getRoomEquipmentsConsumptionByHabitationId() {
    }
}