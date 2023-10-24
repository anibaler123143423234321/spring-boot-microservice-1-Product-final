package com.dagnerchuman.springbootmicroservice1Product.service;

import com.dagnerchuman.springbootmicroservice1Product.model.Producto;

import java.util.List;

public interface ProductoService {
    Producto saveProducto(Producto producto);

    void deleteProducto(Long productoId);

    List<Producto> findAllProductos();


    void deleteAllProductos();

    List<Producto> findSiguientesProductos(int posicion, int cantidad);

    Producto actualizarProducto(Long productoId, Producto nuevoProducto);


    Producto findProductoById(Long productoId);
}
