package com.Perfulandia.producto;

import com.Perfulandia.producto.model.Producto;
import com.Perfulandia.producto.repository.ProductoRepository;
import com.Perfulandia.producto.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockitoBean
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll(){
        when(productoRepository.findAll()).thenReturn(List.of(new Producto(1,"Asad bourbone", "perfumes arabes",27990,34,"Perfume arabe asad bourbon de lattaffa" )));

        List<Producto> productos = productoService.findAll();

        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    public void testFindByid() {
        Long codigo = 1L;
        Producto producto = new Producto(codigo, "Asad bourbone", "perfumes arabes",27990,34,"Perfume arabe asad bourbon de lattaffa");

        when(productoRepository.findById(codigo)).thenReturn(Optional.of(producto));

        Producto found = productoService.findByid(codigo);

        assertNotNull(found);
        assertEquals(codigo, found.getId_producto());
    }

    @Test
    public void testSave() {
        Producto producto = new Producto(1,"Asad bourbone", "perfumes arabes",27990,34,"Perfume arabe asad bourbon de lattaffa");

        when(productoRepository.save(producto)).thenReturn(producto);

        Producto saved = productoService.save(producto);

        assertNotNull(saved);
        assertEquals("Asad bourbone", saved.getNombre_producto());
    }

    @Test
    public void testDeleteById() {
        Long codigo = 1L;

        doNothing().when(productoRepository).deleteById(codigo);

        productoService.delete(codigo);

        verify(productoRepository, times(1)).deleteById(codigo);
    }
}
