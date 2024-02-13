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
}
