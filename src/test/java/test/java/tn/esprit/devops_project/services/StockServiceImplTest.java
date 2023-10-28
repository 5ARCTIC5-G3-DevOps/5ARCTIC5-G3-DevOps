package test.java.tn.esprit.devops_project.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void testAddStock() {
            Stock stock1 = new Stock(2L, "TV",20,5, null);
           // stock2.setIdStock(2L);
            stockService.addStock(stock1);
            verify(stockRepository, times(1)).save(stock1);
            System.out.println(stock1);
            System.out.println(" Create is working correctly...!!");
    }

    @Test
    void testRetrieveStock() {
        Stock stock2 = new Stock(3L, "Machines à laver",15,5, null);
        //stock2.setIdStock(3L);

        when(stockRepository.findById(3L)).thenReturn(Optional.of(stock2));
        stockService.retrieveStock(3L);
        assertNotNull(stock2);

        System.out.println("get ==> " + "id Stock: " +stock2.getIdStock()+ " & title: "+stock2.getTitle());
        System.out.println(" Retrieve is working correctly...!!");

    }

    @Test
    void testRetrieveAllStock() {
        List<Stock> StockList = new ArrayList<>() {
            {
                add(new Stock(1L,"mmm",10,3,null));
                add(new Stock(2L,"sss",12,2,null));
                add(new Stock(3L,"www",12,6,null));
            }};

        when(stockService.retrieveAllStock()).thenReturn(StockList);
        //test
        List<Stock> sList = stockService.retrieveAllStock();
        assertEquals(3, sList.size());
        System.out.println(" Retrieve all is working correctly...!!");

    }


}