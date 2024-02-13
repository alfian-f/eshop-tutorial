package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product savedProduct = productService.create(product);

        assertNotNull(savedProduct.getProductId());
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testGetProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        List<Product> productList = List.of(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        Product retrievedProduct = productService.get(1);

        assertNotNull(retrievedProduct);
        assertEquals(product.getProductId(), retrievedProduct.getProductId());
        assertEquals(product.getProductName(), retrievedProduct.getProductName());
        assertEquals(product.getProductQuantity(), retrievedProduct.getProductQuantity());
    }

    @Test
    void testGetProductNotFound() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        List<Product> productList = List.of(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        Product retrievedProduct = productService.get(2);

        assertNull(retrievedProduct);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        List<Product> productList = List.of(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        when(productRepository.delete(product)).thenReturn(true);

        boolean deletedProduct = productService.delete(1);

        assertTrue(deletedProduct);
    }

    @Test
    void testDeleteProductNotFound() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        List<Product> productList = List.of(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        boolean deletedProduct = productService.delete(2);

        assertFalse(deletedProduct);
    }

    @Test
    void testEditProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        when(productRepository.edit(product2)).thenReturn(product2);

        Product editedProduct = productService.edit(product2);

        assertEquals(product2.getProductName(), editedProduct.getProductName());
        assertEquals(product2.getProductQuantity(), editedProduct.getProductQuantity());
        assertNotEquals(product1.getProductName(), editedProduct.getProductName());
        assertNotEquals(product1.getProductQuantity(), editedProduct.getProductQuantity());
    }
}
