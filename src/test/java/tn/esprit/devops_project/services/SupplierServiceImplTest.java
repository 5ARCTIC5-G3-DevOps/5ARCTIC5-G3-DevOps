package tn.esprit.devops_project.services;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class SupplierServiceImplTest {

    @Autowired
    @InjectMocks
    private SupplierServiceImpl supplierService;


   @Autowired
   @Mock
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierServiceImpl supplierServices;
    @BeforeEach
    void setUp() {
        supplierRepository = mock(SupplierRepository.class);
        supplierService = new SupplierServiceImpl(supplierRepository);

    }
    @Test
    void retrieveAllSuppliers() {
        List<Supplier> l = supplierService.retrieveAllSuppliers();

        assertNotNull(l);
    }

    ///@AfterEach
     public void deletellA() {
        supplierRepository.deleteAll();
    }

    @Test
    @Order(0)
    void addSupplier() {

        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);
        supplier.setCode("supplier@example.com");
        //supplier.setIdSupplier(10L);
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);
        supplier.setCode("code");
        supplierServices.addSupplier(supplier);
        Supplier savedsup = supplierServices.addSupplier(supplier);

        assertNotNull(savedsup);

    }


    @Test
    @Order(1)
    void retrieveSupplier() {
        Supplier supp = supplierServices.retrieveSupplier(1L);



     assertNotNull(supp);


    }
    @Test
    void retrieveSupplierMOCK() {

        Supplier testsupplier = new Supplier();
        testsupplier.setIdSupplier(1L);
        testsupplier.setCode("supplier@example.com");


        when(supplierRepository.findById(1L)).thenReturn(Optional.of(testsupplier));

        Supplier retrievedSupplier = supplierService.retrieveSupplier(1L);

        assertEquals(testsupplier, retrievedSupplier);


    }

    @Test
    void deleteSupplier() {
        doNothing().when(supplierRepository).deleteById(1L);
        supplierService.deleteSupplier(1L);

        verify(supplierRepository).deleteById(1L);
    }

    @Order(1)
    @Test
    void updateSupplier() {
        Supplier supp = supplierServices.retrieveSupplier(1L);
        supp.setCode("update");
        supplierService.updateSupplier(supp);
    }




}
