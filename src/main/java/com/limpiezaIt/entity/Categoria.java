package com.limpiezaIt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "categorias")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

    @Column(nullable = false)
    private Boolean activo = true;

    //Definimos la relacion y decimos que: Una categoria puede tener muchos productos
    @OneToMany(mappedBy = "categoria")
    //Usamos JsonIgnore para evitar referencia ciclica al momento de hacer un get
    @JsonIgnore
    private List<Producto> productos = new ArrayList<>();


}
