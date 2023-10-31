package test.java.tn.esprit.devops_project.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.DevOps_ProjectSpringBootApplication;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@SpringBootTest(classes = DevOps_ProjectSpringBootApplication.class)
@Slf4j
class StockServiceImplTest {
    @Mock
    @Autowired
    private StockRepository stockRepository;

    @InjectMocks
    @Autowired
    private StockServiceImpl stockService;
    private Stock testStock;

    @BeforeEach
    void setUp() {
        stockRepository = mock(StockRepository.class);
        stockService = new StockServiceImpl(stockRepository);
        testStock = new Stock(1L, "Test Stock", null);

    }
    @Test
    void testRetrieveStock() {
        Stock stock2 = new Stock(3L, "Machines à laver", null);

        Mockito.lenient().when(stockRepository.findById(3L)).thenReturn(Optional.of(stock2));
        stockService.retrieveStock(3L);
        assertNotNull(stock2);

        System.out.println("get ==> " + "id Stock: " +stock2.getIdStock()+ " & title: "+stock2.getTitle());
        System.out.println(" Retrieve is working correctly...!!");
    }


    @Test
    void testAddStock1() {
        Stock stock1 = new Stock(2L, "TV", null);
        stockService.addStock(stock1);
        verify(stockRepository, times(1)).save(stock1);
        System.out.println(" Add is working correctly...!!");
    }

    @Test
    void testDeleteStock(){
        Stock stock1 = new Stock(4L,"Cafitére",null);

        Mockito.lenient().when(stockRepository.findById(stock1.getIdStock())).thenReturn(Optional.of(stock1));

        stockService.deleteStock(4L);
        verify(stockRepository).deleteById(stock1.getIdStock());

        System.out.println("Stock ==> " + "id Stock: " +stock1.getIdStock()+ " & title: "+stock1.getTitle());
        System.out.println(" Delete is working correctly...!!");
    }

    @Test
    void testRetrieveAllStock1() {
        List<Stock> StockList = new ArrayList<>() {
            {
                add(new Stock(1L,"mmm",null));
                add(new Stock(2L,"sss",null));
                add(new Stock(3L,"www",null));
            }};

        when(stockService.retrieveAllStock()).thenReturn(StockList);
        //test
        List<Stock> sList = stockService.retrieveAllStock();
        assertEquals(3, sList.size());
        System.out.println(" Retrieve All is working correctly...!!");

    }

    ////JUnit


    @AfterEach
    public void tearDown() {
        stockRepository.deleteAll();
    }

    @Test
    void testAddStock() {
        Stock testStock1 = new Stock(1L, "Test Stock", null);
        stockService.addStock(testStock1);
        //assertNotNull(testStock.getIdStock());
        log.info("Added Stock: {}", testStock1);
    }






}