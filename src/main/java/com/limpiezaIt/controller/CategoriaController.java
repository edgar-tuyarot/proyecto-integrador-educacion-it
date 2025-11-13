package com.limpiezaIt.controller;


import com.limpiezaIt.entity.Categoria;
import com.limpiezaIt.service.impl.CategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    CategoriaServiceImpl categoriaServiceImpl;

    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerTodas(){
        List<Categoria> categorias = categoriaServiceImpl.obtenerTodas();

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId (@PathVariable long id){
        Optional<Categoria> categoria = categoriaServiceImpl.buscarPorId(id);

        return categoria.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria){
        try{
            Categoria nuevaCategoria = categoriaServiceImpl.guardarCategoria(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }




}
