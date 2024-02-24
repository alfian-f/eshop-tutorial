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
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product savedProduct = productService.create(product);

        assertNotNull(savedProduct.getId());
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
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

        when(productService.findById("1")).thenReturn(product);

        Product retrievedProduct = productService.findById("1");

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

        Product retrievedProduct = productService.findById("2");

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

        when(productService.deleteProductById("1")).thenReturn(true);

        boolean deletedProduct = productService.deleteProductById("1");

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

        boolean deletedProduct = productService.deleteProductById("2");

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

        when(productRepository.update(product1.getId(), product2)).thenReturn(product2);

        Product editedProduct = productService.update(product1.getId(), product2);

        assertEquals(product2.getProductName(), editedProduct.getProductName());
        assertEquals(product2.getProductQuantity(), editedProduct.getProductQuantity());
        assertNotEquals(product1.getProductName(), editedProduct.getProductName());
        assertNotEquals(product1.getProductQuantity(), editedProduct.getProductQuantity());
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        List<Product> productList = List.of(product1);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> retrievedProducts = productService.findAll();

        assertEquals(productList.size(), retrievedProducts.size());
    }
}
