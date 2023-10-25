package test.java.tn.esprit.devops_project.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.DevOps_ProjectSpringBootApplication;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;


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
        new  Invoice(20L, 12F, 15F, dateFormat.parse("2023-08-08"), dateFormat.parse("2023-09-09"), false,null,null);
    }

    @AfterEach
    public void tearDown(){
        invoiceRepository.deleteAll();
    }




}