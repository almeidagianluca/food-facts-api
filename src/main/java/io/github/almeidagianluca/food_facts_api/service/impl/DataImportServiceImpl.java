package io.github.almeidagianluca.food_facts_api.service.impl;

import com.google.gson.Gson;
import io.github.almeidagianluca.food_facts_api.model.CronImport;
import io.github.almeidagianluca.food_facts_api.model.Product;
import io.github.almeidagianluca.food_facts_api.model.ProductStatus;
import io.github.almeidagianluca.food_facts_api.repository.CronImportRepository;
import io.github.almeidagianluca.food_facts_api.repository.ProductRepository;
import io.github.almeidagianluca.food_facts_api.service.DataImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

@Service
@Slf4j
public class DataImportServiceImpl implements DataImportService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CronImportRepository cronImportRepository;
    private static final String INDEX_URL = "https://challenges.coode.sh/food/data/json/index.txt";
    private static final String BASE_URL = "https://challenges.coode.sh/food/data/json/";
    @Value("${max.product.import}")
    private int maxProductsImport;
    @Override
    @Scheduled(cron = "${schedule.cron}")
    public void importData() {
        String importStatus = "success";
        try {
            List<String> filenames = getFilenames();
            for (String filename : filenames) {
                String fileUrl = BASE_URL + filename;
                processFile(fileUrl);
            }
        } catch (Exception e) {
            importStatus = "error";
            log.error("Error importing data: " + e.getMessage());
        }
        cronImportRepository.save(CronImport.builder().date(new Date()).status(importStatus).build());
    }

    protected List<String> getFilenames() throws Exception {
        URL url = new URL(INDEX_URL);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return in.lines().collect(Collectors.toList());
        }
    }

    protected void processFile(String fileUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(fileUrl).openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream();
             GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInputStream))) {

            Gson gson = new Gson();
            String line;
            int productCount = 0;

            while ((line = reader.readLine()) != null && productCount < maxProductsImport) {
                try {
                    Product product = gson.fromJson(line, Product.class);
                    setCustomProductFields(product);
                    saveProduct(product);
                    productCount++;
                } catch (Exception e) {
                    log.error("Error processing product: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            log.error("Error reading file: " + e.getMessage());
        } finally {
            connection.disconnect();
        }
    }

    protected void saveProduct(Product product) {
        Product existProduct = productRepository.findByCode(product.getCode());
        if (existProduct == null) {
            productRepository.save(product);
        }

    }

    private void setCustomProductFields(Product product) {
        product.setCode(product.getCode().replaceAll("\"", ""));
        product.setImportedT(new Date());
        product.setStatus(ProductStatus.DRAFT);
    }
}
