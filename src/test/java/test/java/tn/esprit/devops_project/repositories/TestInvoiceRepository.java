package test.java.tn.esprit.devops_project.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.DevOps_ProjectSpringBootApplication;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest(classes = DevOps_ProjectSpringBootApplication.class)
public class TestInvoiceRepository {
    @Autowired
     InvoiceRepository invoiceRepository;

    @Autowired
     InvoiceServiceImpl invoiceService;


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @BeforeEach
    public void setUp() throws ParseException {
        Invoice testInvoice=new  Invoice(20L, 12F, 15F, dateFormat.parse("2023-08-08"), dateFormat.parse("2023-09-09"), false);
    }

    @AfterEach
    public void tearDown(){
        invoiceRepository.deleteAll();
    }


   /* @Test
    void testRetrieveAllInvoices() throws ParseException {


        List<Invoice> invoiceList = new ArrayList<>() {
            {
                add(new Invoice(2L, 12F, 15F, dateFormat.parse("2023-08-08"), dateFormat.parse("2023-09-09"), true));
                add(new Invoice(3L, 13F, 16F, dateFormat.parse("2023-08-08"), dateFormat.parse("2023-09-09"), true));
                add(new Invoice(4L, 14F, 17F, dateFormat.parse("2023-08-08"), dateFormat.parse("2023-09-09"), true));
            }
        };

        when(invoiceService.retrieveAllInvoices()).thenReturn(invoiceList);

        // Test
        List<Invoice> invoiceListTest = invoiceService.retrieveAllInvoices();
        assertEquals(3, invoiceListTest.size());
        System.out.println("Retrieve all invoices is working correctly...!!");
    }*/

}
