package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;

import  static  org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @Mock
    private Model model;
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){MockitoAnnotations.openMocks(this);}

    @Test
    void testCreateProductGet() {
        String page = productController.createProductPage(model);
        assertEquals("CreateProduct", page);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        when(productService.create(product)).thenReturn(product);

        String page = productController.createProductPost(product, model);

        assertEquals("redirect:list", page);
    }

    @Test
    void testEditProductGet() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        when(productService.findById("1")).thenReturn(product);

        String page = productController.editProductPage("1", model);

        assertEquals("EditProduct", page);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        String page = productController.editProductPost(product, model);

        assertEquals("redirect:list", page);
    }

    @Test
    void testListProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        List<Product> productList = List.of(product1, product2);

        when(productService.findAll()).thenReturn(productList);

        String page = productController.productListPage(model);

        assertEquals("ProductList", page);
    }

    @Test
    void testControllerDeleteProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        String page = productController.deleteProduct("1");

        assertEquals("redirect:/product/list", page);
    }
}
