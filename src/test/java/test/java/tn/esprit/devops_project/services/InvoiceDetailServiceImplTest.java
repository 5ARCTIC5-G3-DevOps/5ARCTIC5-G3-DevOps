package test.java.tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.InvoiceDetail;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.services.InvoiceDetailServiceImpl;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvoiceDetailServiceImplTest {
    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @InjectMocks
    InvoiceDetailServiceImpl invoiceDetailService;
    @InjectMocks
    InvoiceServiceImpl invoiceService;

    @Test
    void testRetrieveAllInvoiceDetails() {
        List<InvoiceDetail> invoiceDetailList = new ArrayList<>() {
            {
                add(new InvoiceDetail(1L, 15, 16F));
                add(new InvoiceDetail(2L, 16, 17F));
                add(new InvoiceDetail(3L, 17, 18F));

            }
        };

        when(invoiceDetailService.retriveAllInvoicesDetail()).thenReturn(invoiceDetailList);

        // Test
        List<InvoiceDetail> invoiceDetailListTest = invoiceDetailService.retriveAllInvoicesDetail();
        assertEquals(3, invoiceDetailListTest.size());
        System.out.println("Retrieve all invoices details is working correctly...!!");
    }
    @Test
    void testGetInvoiceDetail() {
        // Arrange
        InvoiceDetail invoiceDetail = new InvoiceDetail(4L, 15, 16F);
        when(invoiceDetailRepository.findById(invoiceDetail.getIdInvoiceDetail())).thenReturn(Optional.of(invoiceDetail));

        // Act
        InvoiceDetail retrievedInvoiceDetail = invoiceDetailService.getInvoiceDetail(invoiceDetail.getIdInvoiceDetail());

        // Assert
        assertNotNull(retrievedInvoiceDetail);
        assertSame(invoiceDetail, retrievedInvoiceDetail);

        // Verify that invoiceRepository method was called
        verify(invoiceDetailRepository, times(1)).findById(invoiceDetail.getIdInvoiceDetail());

        // Test the case when the invoice is not found
        // Re-stub the behavior for this case
        when(invoiceDetailRepository.findById(invoiceDetail.getIdInvoiceDetail())).thenReturn(Optional.empty());

        // Assert that the service throws a NullPointerException with the expected message when the invoice is not found
        Exception exception = assertThrows(NullPointerException.class, () -> invoiceDetailService.getInvoiceDetail(invoiceDetail.getIdInvoiceDetail()));
        assertEquals("Invoice detail not found", exception.getMessage());
    }


    @Test
    void testAddInvoiceDetail() {
        // Arrange
        InvoiceDetail invoiceDetail = new InvoiceDetail(5L, 5, 10.0F); // Create a sample InvoiceDetail

        // Mock the behavior of the invoiceDetailRepository to return the saved InvoiceDetail
        when(invoiceDetailRepository.save(invoiceDetail)).thenReturn(invoiceDetail);

        // Act
        InvoiceDetail savedInvoiceDetail = invoiceDetailService.addInvoiceDetail(invoiceDetail);

        // Assert
        assertNotNull(savedInvoiceDetail); // Verify that the saved InvoiceDetail is not null
        assertSame(invoiceDetail, savedInvoiceDetail); // Verify that the saved InvoiceDetail is the same as the sample

        // Verify that invoiceDetailRepository method was called
        verify(invoiceDetailRepository, times(1)).save(invoiceDetail);
        System.out.println("invoice detail added successfully ;");
    }

    @Test
    void testDeleteInvoiceDetail() {
        // Arrange
        long idInvoiceDetailToDelete = 1L;

        // Act
        invoiceDetailService.deleteInvoiceDetail(idInvoiceDetailToDelete);

        // Verify that invoiceDetailRepository method was called to delete the InvoiceDetail
        verify(invoiceDetailRepository, times(1)).deleteById(idInvoiceDetailToDelete);

        // Assert that the deletion was successful
        verifyNoMoreInteractions(invoiceDetailRepository);
        System.out.println("detail invoice is deleted successfully !!");
    }

    /*@Test
    void testAddInvoiceDetailToInvoice() throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Arrange
        long invoiceId = 1L;
        Invoice invoice = new Invoice(1L, 100.0F, 120.0F, new Date(), new Date(), false);
        InvoiceDetail invoiceDetail = new InvoiceDetail(1L, 5, 10.0F);

        // Mock the behavior of the repository methods
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(invoiceDetailRepository.save(invoiceDetail)).thenReturn(invoiceDetail);

        // Act
        Invoice updatedInvoice = invoiceService.addInvoiceDetailToInvoice(invoiceId, invoiceDetail);

        // Assert
        assertNotNull(updatedInvoice); // Verify that the updated Invoice is not null
        assertTrue(updatedInvoice.getInvoiceDetails().contains(invoiceDetail)); // Verify that the Invoice contains the InvoiceDetail

        // Verify that repository methods were called
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(invoiceDetailRepository, times(1)).save(invoiceDetail);
    }*/
}
