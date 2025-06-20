package com.Perfulandia.producto.controller;

import com.Perfulandia.producto.model.Producto;
import com.Perfulandia.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Obtiene una lista con todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion realizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class)))
    })
    public ResponseEntity<List<Producto>> listar(){
        List<Producto> productos = productoService.findAll();
        if (productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    @Operation(summary = "Guardar un producto", description = "Recibe todos los datos necesarios para guardar un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto guardado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida (datos faltantes o erróneos)")
    })
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto){
        Producto productoNuevo = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por id", description = "Recibe un parametro en la ruta correspondinte al id de un producto y obtiene el producto correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación realizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Producto> buscar(@Parameter(description = "id del producto a buscar") @PathVariable Integer id ){
        try {
            Producto producto = productoService.findByid(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description = "Actualiza los datos de un producto existente especificando su id en la ruta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida (datos faltantes o erróneos)"),

            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Producto> actualizar(@Parameter(description = "id del producto a actualizar") @PathVariable Integer id, @RequestBody Producto producto)    {
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
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto especificando su id en la ruta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Producto> eliminar(@Parameter(description = "id del producto a eliminar") @PathVariable Long id){
        try{
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
