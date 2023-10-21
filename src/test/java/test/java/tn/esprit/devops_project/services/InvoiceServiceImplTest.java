package test.java.tn.esprit.devops_project.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@ExtendWith(MockitoExtension.class)

public class InvoiceServiceImplTest {
    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;




    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
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
    }


    @Test
    void testCancelInvoice() throws ParseException {
        // Arrange
        Invoice invoice1=new Invoice(2L, 12F, 15F, dateFormat.parse("2023-08-08"), dateFormat.parse("2023-09-09"), true);


        // Mock the behavior of the invoiceRepository
        when(invoiceRepository.findById(invoice1.getIdInvoice())).thenReturn(Optional.of(invoice1));

        // Act
        invoiceService.cancelInvoice(invoice1.getIdInvoice());

        // Assert
        assertTrue(invoice1.getArchived()); // Verify that the invoice is archived

        // Verify that invoiceRepository methods were called
        verify(invoiceRepository, times(1)).findById(invoice1.getIdInvoice());
        verify(invoiceRepository, times(1)).save(invoice1);

        // You can also use additional assertions to further validate the behavior
        // For example, you can check if the invoiceRepository.updateInvoice method was not called, assuming it's not needed in this test.
        verify(invoiceRepository, never()).updateInvoice(any(Long.class));

    }


    @Test
    void testRetrieveInvoice() throws ParseException {
        // Arrange
        Invoice invoice2 = new Invoice(6L, 12F, 15F, dateFormat.parse("2023-08-08"), dateFormat.parse("2023-09-09"), true);

        // Stub the behavior of the invoiceRepository to return invoice2 when findById is called with invoice2.getIdInvoice()
        when(invoiceRepository.findById(invoice2.getIdInvoice())).thenReturn(Optional.of(invoice2));

        // Act
        Invoice retrievedInvoice = invoiceService.retrieveInvoice(invoice2.getIdInvoice());

        // Assert
        assertNotNull(retrievedInvoice);
        assertSame(invoice2, retrievedInvoice);

        // Verify that invoiceRepository method was called
        verify(invoiceRepository, times(1)).findById(invoice2.getIdInvoice());

        // Test the case when the invoice is not found
        // Re-stub the behavior for this case
        when(invoiceRepository.findById(2L)).thenReturn(Optional.empty());

        // Assert that the service throws a NullPointerException when the invoice is not found
        assertThrows(NullPointerException.class, () -> invoiceService.retrieveInvoice(2L));
    }


    @Test
    void testGetInvoicesBySupplier() throws ParseException {
        // Arrange
      //  Long idSupplier = 1L;
        Supplier supplier = new Supplier(); // Create a sample supplier
        Invoice invoice3=new Invoice(7L,12F,15F,dateFormat.parse("2023-08-08"),dateFormat.parse("2023-09-09"),true);

        // Mock the behavior of the supplierRepository
        when(supplierRepository.findById(supplier.getIdSupplier())).thenReturn(Optional.of(supplier));
        when(supplier.getInvoices()).thenReturn(Collections.singleton(invoice3)); // Return the sample invoice

        // Act
        List<Invoice> invoices = invoiceService.getInvoicesBySupplier(supplier.getIdSupplier());

        // Assert
        assertNotNull(invoices); // Verify that the returned list is not null
        assertEquals(1, invoices.size()); // Verify that one invoice is returned

        // Verify that supplierRepository methods were called
        verify(supplierRepository, times(1)).findById(supplier.getIdSupplier());
    }


    @Test
    void testAssignOperatorToInvoice()  throws ParseException {
        // Arrange
        Long idOperator = 1L;
        //Long idInvoice = 2L;

        Invoice invoice4=new Invoice(8L,12F,15F,dateFormat.parse("2023-08-08"),dateFormat.parse("2023-09-09"),true);
        Operator operator=new Operator();

        // Mock the behavior of the repositories
        when(invoiceRepository.findById(invoice4.getIdInvoice())).thenReturn(Optional.of(invoice4));
        when(operatorRepository.findById(idOperator)).thenReturn(Optional.of(operator));
        when(operatorRepository.save(operator)).thenReturn(operator);

        // Act
        invoiceService.assignOperatorToInvoice(idOperator, invoice4.getIdInvoice());

        // Assert
        assertTrue(operator.getInvoices().contains(invoice4)); // Verify that the invoice is added to the operator's invoices

        // Verify that repository methods were called
        verify(invoiceRepository, times(1)).findById(invoice4.getIdInvoice());
        verify(operatorRepository, times(1)).findById(idOperator);
        verify(operatorRepository, times(1)).save(operator);

        // You can also use additional assertions to further validate the behavior
    }




    @Test
    void testGetTotalAmountInvoiceBetweenDates() throws ParseException{
        // Arrange

        Date startDate = dateFormat.parse("2023-08-01");
        Date endDate = dateFormat.parse("2023-08-31");
        float expectedTotalAmount = 100.0F; // Set your expected total amount

        // Mock the behavior of the invoiceRepository to return the expected total amount
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedTotalAmount);

        // Act
        float totalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Assert
        assertEquals(expectedTotalAmount, totalAmount, 0.01); // Use a delta for float comparison

        // Verify that invoiceRepository method was called
        verify(invoiceRepository, times(1)).getTotalAmountInvoiceBetweenDates(startDate, endDate);
    }

    }





