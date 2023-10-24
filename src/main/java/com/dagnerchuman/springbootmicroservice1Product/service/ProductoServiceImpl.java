package com.dagnerchuman.springbootmicroservice1Product.service;


import com.dagnerchuman.springbootmicroservice1Product.model.Producto;
import com.dagnerchuman.springbootmicroservice1Product.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto saveProducto(Producto producto)
    {
        producto.setFechaCreacion(LocalDateTime.now());
        return productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Long productoId)
    {
        productoRepository.deleteById(productoId);
    }

    @Override
    public List<Producto> findAllProductos()
    {
        return productoRepository.findAll();
    }



    @Override
    public void deleteAllProductos() {
        productoRepository.deleteAll();
    }

    @Override
    public List<Producto> findSiguientesProductos(int posicion, int cantidad) {
        List<Producto> todosLosProductos = productoRepository.findAll();
        List<Producto> siguientesProductos = new ArrayList<>();

        for (int i = posicion; i < Math.min(posicion + cantidad, todosLosProductos.size()); i++) {
            siguientesProductos.add(todosLosProductos.get(i));
        }

        return siguientesProductos;
    }

    @Override
    public Producto actualizarProducto(Long productoId, Producto nuevoProducto) {
        Optional<Producto> optionalProducto = productoRepository.findById(productoId);
        if (optionalProducto.isPresent()) {
            Producto productoExistente = optionalProducto.get();

            // Actualiza los campos del producto existente con los valores del nuevo producto
            productoExistente.setStock(nuevoProducto.getStock());

            // Guarda el producto actualizado
            return productoRepository.save(productoExistente);
        } else {
            throw new EntityNotFoundException("Producto no encontrado con ID: " + productoId);
        }
    }


    @Override
    public Producto findProductoById(Long productoId) {
        Optional<Producto> optionalProducto = productoRepository.findById(productoId);
        if (optionalProducto.isPresent()) {
            return optionalProducto.get();
        } else {
            throw new EntityNotFoundException("Producto no encontrado con ID: " + productoId);
        }
    }

}
