package com.dagnerchuman.springbootmicroservice1Product.repository;

import com.dagnerchuman.springbootmicroservice1Product.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
