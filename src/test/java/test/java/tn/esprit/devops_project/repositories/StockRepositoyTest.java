package test.java.tn.esprit.devops_project.repositories;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.devops_project.DevOps_ProjectSpringBootApplication;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(classes = DevOps_ProjectSpringBootApplication.class)
public class StockRepositoyTest {
  /*  @Autowired private StockRepository stockRepository;

    @Autowired private StockServiceImpl stockService;

    private Stock testStock;

    @BeforeEach
    public void setUp() {
        testStock = new Stock(1L, "Test Stock", null);
    }

   @AfterEach
    public void tearDown() {
        stockRepository.deleteAll();
    }

    @Test
    public void testAddStock() {
        stockService.addStock(testStock);
        assertNotNull(testStock.getIdStock());
        log.info("Added Stock: {}", testStock);
    }

    @Test
    public void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<>() {
            {
                add(new Stock(3L, "Stock 1", null));
                add(new Stock(4L, "Stock 2", null));
                add(new Stock(5L, "Stock 3", null));
                add(new Stock(6L, "Stock 4", null));

            }
        };
        stockRepository.saveAll(stockList);
        List<Stock> retrievedList = stockService.retrieveAllStock();
        assertEquals(4, retrievedList.size());
        log.info("Retrieved Stock List: {}", retrievedList);
    }*/

}
