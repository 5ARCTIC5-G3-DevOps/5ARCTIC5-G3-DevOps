package test.java.tn.esprit.devops_project.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
class StockServiceImplTestMock {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void TestRetrieveStock() {
        Stock stock2 = new Stock(3L, "Machines à laver", null);

        when(stockRepository.findById(3L)).thenReturn(Optional.of(stock2));
        stockService.retrieveStock(3L);
        assertNotNull(stock2);

        System.out.println("get ==> " + "id Stock: " +stock2.getIdStock()+ " & title: "+stock2.getTitle());
        System.out.println(" Retrieve is working correctly...!!");
    }


    @Test
    void TestDeleteStock(){
        Stock stock1 = new Stock(4L,"Cafitére",null);

        Mockito.lenient().when(stockRepository.findById(stock1.getIdStock())).thenReturn(Optional.of(stock1));

        stockService.deleteStock(4L);
        verify(stockRepository).deleteById(stock1.getIdStock());

        System.out.println("Stock ==> " + "id Stock: " +stock1.getIdStock()+ " & title: "+stock1.getTitle());
        System.out.println(" Delete is working correctly...!!");
    }




}