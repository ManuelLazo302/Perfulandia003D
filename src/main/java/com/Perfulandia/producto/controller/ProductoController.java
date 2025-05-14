package com.Perfulandia.producto.controller;

import com.Perfulandia.producto.model.Producto;
import com.Perfulandia.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        List<Producto> productos = productoService.findAll();
        if (productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto){
        Producto productoNuevo = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Integer id ){
        try {
            Producto producto = productoService.findByid(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @RequestBody Producto producto)    {
        try{
            Producto prod = productoService.findByid(id);
            prod.setId_producto(id);
            prod.setNombre_producto(producto.getNombre_producto());
            prod.setCategoria_producto(producto.getCategoria_producto());
            prod.setPrecio(producto.getPrecio());
            prod.setStock_producto(producto.getStock_producto());
            prod.setDescripcion_producto(producto.getDescripcion_producto());
            productoService.save(prod);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> eliminar(@PathVariable Long id){
        try{
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
