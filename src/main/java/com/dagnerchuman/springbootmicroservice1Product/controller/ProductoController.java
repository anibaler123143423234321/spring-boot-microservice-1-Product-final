package com.dagnerchuman.springbootmicroservice1Product.controller;

import com.dagnerchuman.springbootmicroservice1Product.model.Producto;
import com.dagnerchuman.springbootmicroservice1Product.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //http://locahost:3333/api/producto
    @PostMapping
    public ResponseEntity<?> saveProducto(@RequestBody Producto producto)
    {

        return new ResponseEntity<>(
                productoService.saveProducto(producto) ,
                HttpStatus.CREATED
        );
    }

    //http://locahost:3333/api/producto/1000
    @DeleteMapping("/{productoId}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long productoId)
    {
        productoService.deleteProducto(productoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //http://locahost:3333/api/producto
    @GetMapping
    public ResponseEntity<?> getAllProductos()
    {
        return ResponseEntity.ok(productoService.findAllProductos());
    }




    // Agrega este método para eliminar todos los registros de la tabla de productos
    @DeleteMapping("/eliminar-todos")
    public ResponseEntity<?> deleteAllProductos() {
        productoService.deleteAllProductos();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/siguientes")
    public ResponseEntity<?> getSiguientesProductos(
            @RequestParam("posicion") int posicion,
            @RequestParam("cantidad") int cantidad
    ) {
        List<Producto> productos = productoService.findSiguientesProductos(posicion, cantidad);

        if (productos.isEmpty()) {
            return ResponseEntity.ok("No hay más productos disponibles");
        }

        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long productoId, @RequestBody Producto nuevoProducto) {
        try {
            Producto productoActualizado = productoService.actualizarProducto(productoId, nuevoProducto);
            return ResponseEntity.ok(productoActualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/{productoId}")
    public ResponseEntity<?> getProductoById(@PathVariable Long productoId) {
        try {
            Producto producto = productoService.findProductoById(productoId);
            return ResponseEntity.ok(producto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + productoId);
        }
    }

    // Nuevo endpoint para buscar productos por negocio
    @GetMapping("/pornegocio/{negocioId}")
    public ResponseEntity<?> getProductosPorNegocio(@PathVariable Long negocioId) {
        List<Producto> productos = productoService.findProductosPorNegocio(negocioId);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/porcategoria/{categoriaId}")
    public ResponseEntity<?> getProductosPorCategoria(@PathVariable Long categoriaId) {
        List<Producto> productos = productoService.findProductosPorCategoria(categoriaId);
        return ResponseEntity.ok(productos);
    }

}