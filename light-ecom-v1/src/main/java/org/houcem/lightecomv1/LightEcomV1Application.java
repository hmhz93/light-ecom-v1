package org.houcem.lightecomv1;

import net.bytebuddy.utility.RandomString;
import org.houcem.lightecomv1.dao.CategoryRepository;
import org.houcem.lightecomv1.dao.ProductRepository;
import org.houcem.lightecomv1.entities.Category;
import org.houcem.lightecomv1.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class LightEcomV1Application implements CommandLineRunner
{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public static void main(String[] args) {
        SpringApplication.run(LightEcomV1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        categoryRepository.save(new Category(null, "Computers", null, null));
        categoryRepository.save(new Category(null, "Printers", null, null));
        categoryRepository.save(new Category(null, "Smart phones", null, null));

        Random random = new Random();
        categoryRepository.findAll().forEach(category -> {

            Product product = new Product();
            product.setName(RandomString.make(18));
            product.setCurrentPrice(100 + random.nextInt(10000));
            product.setAvailable(random.nextBoolean());
            product.setPromotion(random.nextBoolean());
            product.setSelected(random.nextBoolean());
            product.setCategory(category);

            productRepository.save(product);
        });
    }
}
