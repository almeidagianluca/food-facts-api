package io.github.almeidagianluca.food_facts_api.service.impl;

import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.repository.CronImportRepository;
import io.github.almeidagianluca.food_facts_api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataImportServiceImplTest {

    @InjectMocks
    private DataImportServiceImpl dataImportService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CronImportRepository cronImportRepository;

    @Test
    void importData() throws Exception {
        DataImportServiceImpl spyService = spy(dataImportService);
        List<String> filenames = Arrays.asList("file1.json", "file2.json");
        doReturn(filenames).when(spyService).getFilenames();
        doNothing().when(spyService).processFile(anyString());

        spyService.importData();

        verify(spyService, times(2)).processFile(anyString());
    }
    @Test
    void saveProductNewProduct() {
        Product product = Product.builder().build();
        product.setCode("123");
        when(productRepository.findByCode("123")).thenReturn(null);
        when(productRepository.save(any())).thenReturn(Product.builder().build());

        dataImportService.saveProduct(product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void saveProductExistingProduct() {
        Product existingProduct = Product.builder().build();
        existingProduct.setCode("123");
        Product newProduct = Product.builder().build();
        newProduct.setCode("123");
        when(productRepository.findByCode("123")).thenReturn(existingProduct);

        dataImportService.saveProduct(newProduct);

        verify(productRepository, never()).save(newProduct);
    }

    @Test
    void processFileSuccess() throws IOException {
        String fileUrl = "https://challenges.coode.sh/food/data/json/products_01.json.gz";
        when(productRepository.save(any())).thenReturn(Product.builder().build());
        ReflectionTestUtils.setField(dataImportService, "maxProductsImport", 1);

        dataImportService.processFile(fileUrl);

        verify(productRepository).save(any(Product.class));
    }

    @Test
    void processFileErrorReadingFile(){
        String fileUrl = "wrongUrl";

        assertThrows(IOException.class, () -> dataImportService.processFile(fileUrl));
    }

    @Test
    void getFilenames() throws Exception {
        List<String> filenames = dataImportService.getFilenames();

        assertEquals(9, filenames.size());
    }

}