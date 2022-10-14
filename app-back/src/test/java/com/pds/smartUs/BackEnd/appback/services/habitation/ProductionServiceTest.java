package com.pds.smartUs.BackEnd.appback.services.habitation;

import com.pds.smartUs.BackEnd.appback.entities.Bepos;
import com.pds.smartUs.BackEnd.appback.entities.EquipmentConsomption;
import com.pds.smartUs.BackEnd.appback.entities.Producteur;
import com.pds.smartUs.BackEnd.appback.entities.Production;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.ProducteurRepository;
import com.pds.smartUs.BackEnd.appback.repositories.habitation.ProductionRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.HttpClientErrorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductionServiceTest {

    @InjectMocks
    private ProductionService productionService;

    @Mock
    private  ProductionRepository productionRepository;
    @Mock
    private  ProducteurRepository producteurRepository;

    private static final Logger logger = Logger.getLogger(HabitationServiceTest.class.getName());

    private static Instant startChrono;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        productionService = new ProductionService(productionRepository,producteurRepository);
    }


    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @BeforeAll
    public static void initChrono() {
        logger.info("Begin of all tests for Production Service");
        startChrono = Instant.now();
    }

    @AfterAll
    public static void durationTest() {
        logger.info("End of all tests for Production Service");
        Instant endChrono = Instant.now();
        long duration = Duration.between(startChrono, endChrono).toMillis();
        logger.info("duration of tests for Production Service : " + duration + "ms");
    }




    @Test
    @Disabled
    @DisplayName("Should check that all producer  elements for a given BEPOS belongs to the same BEPOS")
    void getProducteursBybeposShouldReturnAllProducteurByBeposId() {
        //Given
        Long id = 1L;

        //Desired Result
        Bepos b1 = Bepos.builder().id(id).build();
        Bepos b2 = Bepos.builder().id(id).build();
        Producteur p1 = Producteur.builder().bepos(b1).build();
        Producteur p2 = Producteur.builder().bepos(b2).build();
        List<Producteur> result = Arrays.asList(p1,p2);

        // When
        when(producteurRepository.findAllByBeposId(id)).thenReturn(result);
        //Then
        assertTrue(productionService.getProducteursBybepos(id).stream().allMatch(producteur -> producteur.getBepos().getId().equals(id)));
    }

    @Test
    @DisplayName("Should return Empty when the BEPOS has no Producers")
    void getProducteursBybeposShouldReturnEmpty() {
        Long id = 1L;
        List<Producteur> result = new ArrayList<>();

        when(producteurRepository.findAllByBeposId(id)).thenReturn(result);

        assertEquals(result, productionService.getProducteursBybepos(id));
    }


    @Test
    @Disabled
    void getProductionsOfProducersShouldReturnAllProducteurWithTheirlast10Productions() throws ParseException {
        //given
        Long beposId = 1L;
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(formattedDate);

        //Desired Result
        Bepos b1 = Bepos.builder().id(beposId).build();
        Bepos b2 = Bepos.builder().id(beposId).build();
        Producteur p1 = Producteur.builder().id(1L).bepos(b1).build();
        Producteur p2 = Producteur.builder().bepos(b2).build();


        Production prod1= Production.builder().producteur(p1).id(1L).time(date1).production(10).build();
        Production prod2= Production.builder().producteur(p1).id(2L).production(8).build();
        Production prod3= Production.builder().producteur(p1).id(3L).production(9).build();
        Production prod4= Production.builder().producteur(p1).id(4L).production(7).build();
        Production prod5= Production.builder().producteur(p1).production(8).build();
        Production prod6= Production.builder().producteur(p1).production(12).build();
        Production prod7= Production.builder().producteur(p1).production(13).build();
        Production prod8= Production.builder().producteur(p1).production(12).build();
        List<Production> prods = Arrays.asList(prod1,prod2,prod3,prod4);
        System.out.println(prods);
        Map<String, List<Production>> result = new HashMap<>();

        //expected
        result.put("prod1",prods);
        System.out.println(result);



        // When

        when(productionRepository.findProductionsByTime(10,beposId)).thenReturn(prods);

        //then
        Map<String, List<Production>> productionsForEachProducteur = productionService.getProductionsOfProducers(beposId);
        System.out.println(productionsForEachProducteur);
        System.out.println("productionsForEachProducteur");
        //assertThat(result).is
        assertEquals(prods,productionsForEachProducteur.get("prod1"));
//        assertTrue(productionService.getProductionsOfProducers(beposId).stream().allMatch(producteur -> producteur.getBepos().getId().equals(id)));
    }

    @Test
    @DisplayName("Should contain same number of elements as number given dates -1, in this test we don't care about deo return we just test the size")
    void getProductionsOfProducersBydate() {
        //given
        Long habitationid = 1L;
        LocalDate a[] = new LocalDate[]{LocalDate.of(2022,05,05), LocalDate.of(2022,05,04), LocalDate.of(2022,05,03), LocalDate.of(2022,05,02)};
        ArrayList<LocalDate> dates=new ArrayList<>(Arrays.asList(a));
        //when
        List<Double> result = productionService.getProductionsOfProducersBydate(habitationid,dates);

        //then
        assertTrue(result.size() == 3);

    }
}