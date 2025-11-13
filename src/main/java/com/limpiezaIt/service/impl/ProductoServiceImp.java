package com.limpiezaIt.service.impl;


import com.limpiezaIt.entity.Producto;
import com.limpiezaIt.repository.ProductoRepository;
import com.limpiezaIt.service.interfaces.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImp implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        //verificamos que el producto este en DB
        Optional<Producto> opt = buscarPorId(id);

        //Si esta, se pasa a actualizar
        if(opt.isPresent()){
            Producto productoDB = opt.get();
            productoDB.setNombre(producto.getNombre());
            productoDB.setPrecio(producto.getPrecio());
            productoDB.setStock(producto.getStock());
            productoDB.setSku(producto.getSku());
            //Se guarda
            return productoRepository.save(productoDB);

        }else {
            //Se retorna null si no esta.
            return null;
        }

    }


    @Override
    public boolean desactivarProducto(Long id) {
        Optional<Producto> optionalProducto = buscarPorId(id);
        if(optionalProducto.isPresent()){
            productoRepository.desactivarProducto(id);
            return true;
        }else{
            return false;
        }


    }


}
