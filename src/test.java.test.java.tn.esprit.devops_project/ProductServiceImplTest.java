package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setUp() {
        productService = new ProductServiceImpl(productRepository, stockRepository);
    }

    @Test
    public void testAddProduct() {
        Stock stock = new Stock();
        Product product = new Product();
        product.setTitle("Test Product");
        product.setPrice(20.0f);
        product.setQuantity(10);
        product.setCategory(ProductCategory.ELECTRONICS);

        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
        Mockito.when(productRepository.save(Mockito.any(Product.class)).thenReturn(product);

        Product savedProduct = productService.addProduct(product, 1L);

        assertEquals("Test Product", savedProduct.getTitle());
        assertEquals(20.0f, savedProduct.getPrice());
        assertEquals(10, savedProduct.getQuantity());
        assertEquals(ProductCategory.ELECTRONICS, savedProduct.getCategory());
    }

    @Test
    public void testRetrieveProduct() {
        Product product = new Product();
        product.setIdProduct(1L);
        product.setTitle("Test Product");

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.retrieveProduct(1L);

        assertEquals(1L, retrievedProduct.getIdProduct());
        assertEquals("Test Product", retrievedProduct.getTitle());
    }

    @Test
    public void testRetrieveProductNotFound() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> productService.retrieveProduct(1L));
    }

    @Test
    public void testRetrieveAllProduct() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());

        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveAllProduct();

        assertEquals(3, retrievedProducts.size());
    }

    @Test
    public void testRetrieveProductByCategory() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        Mockito.when(productRepository.findByCategory(ProductCategory.ELECTRONICS)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retrieveProductByCategory(ProductCategory.ELECTRONICS);

        assertEquals(2, retrievedProducts.size());
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testRetrieveProductStock() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        Mockito.when(productRepository.findByStockIdStock(1L)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveProductStock(1L);

        assertEquals(2, retrievedProducts.size());
    }
}