package test.java.tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.InvoiceDetail;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.services.InvoiceDetailServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class InvoiceDetailServiceImplTest {

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @InjectMocks
    InvoiceDetailServiceImpl invoiceDetailService;

    @Test
    void testRetrieveAllInvoiceDetails() {
        List<InvoiceDetail> invoiceDetailList = new ArrayList<>() {
            {
                add(new InvoiceDetail(1L, 15, 16F,null,null));
                add(new InvoiceDetail(2L, 16, 17F,null,null));
                add(new InvoiceDetail(3L, 17, 18F,null,null));

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
        InvoiceDetail invoiceDetail = new InvoiceDetail(4L, 15, 16F,null,null);
        when(invoiceDetailRepository.findById(invoiceDetail.getIdInvoiceDetail())).thenReturn(Optional.of(invoiceDetail));

        // Act
        InvoiceDetail retrievedInvoiceDetail = invoiceDetailService.getInvoiceDetail(invoiceDetail.getIdInvoiceDetail());

        // Assert
        assertNotNull(retrievedInvoiceDetail);
        assertSame(invoiceDetail, retrievedInvoiceDetail);

        // Verify that invoiceRepository method was called
        verify(invoiceDetailRepository, times(1)).findById(invoiceDetail.getIdInvoiceDetail());

        when(invoiceDetailRepository.findById(invoiceDetail.getIdInvoiceDetail())).thenReturn(Optional.empty());

    }


    @Test
    void testAddInvoiceDetail() {
        // Arrange
        InvoiceDetail invoiceDetail = new InvoiceDetail(5L, 5, 10.0F,null,null); // Create a sample InvoiceDetail

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


}
