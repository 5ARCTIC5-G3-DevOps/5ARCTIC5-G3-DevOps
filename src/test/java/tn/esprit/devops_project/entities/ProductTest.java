package tn.esprit.devops_project.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setIdProduct(1L);
        product.setTitle("Test Product");
        product.setPrice(20.0f);
        product.setQuantity(10);
        product.setCategory(ProductCategory.ELECTRONICS);
    }

    @Test
    public void testProductProperties() {
        assertEquals(1L, product.getIdProduct());
        assertEquals("Test Product", product.getTitle());
        assertEquals(20.0f, product.getPrice(), 0.001);  // Use delta for float comparison
        assertEquals(10, product.getQuantity());
        assertEquals(ProductCategory.ELECTRONICS, product.getCategory());
    }

    @Test
    public void testProductSettersAndGetters() {
        product.setIdProduct(2L);
        product.setTitle("Updated Product");
        product.setPrice(30.0f);
        product.setQuantity(5);
        product.setCategory(ProductCategory.CLOTHING);

        assertEquals(2L, product.getIdProduct());
        assertEquals("Updated Product", product.getTitle());
        assertEquals(30.0f, product.getPrice(), 0.001);
        assertEquals(5, product.getQuantity());
        assertEquals(ProductCategory.CLOTHING, product.getCategory());
    }
}
