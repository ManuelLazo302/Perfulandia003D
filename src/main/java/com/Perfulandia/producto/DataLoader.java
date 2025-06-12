package com.Perfulandia.producto;
import com.Perfulandia.producto.model.Producto;
import com.Perfulandia.producto.repository.ProductoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        for(int i = 0; i <50; i++){
            Producto producto = new Producto();
            producto.setNombre_producto(faker.commerce().productName());
            producto.setCategoria_producto(faker.commerce().department());
            producto.setStock_producto(faker.number().numberBetween(1,150));
            producto.setPrecio(faker.number().numberBetween(5990,300000));
            producto.setDescripcion_producto(faker.lorem().sentence());

            productoRepository.save(producto);
        }
    }
}
