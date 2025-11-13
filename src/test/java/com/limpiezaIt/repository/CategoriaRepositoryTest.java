package com.limpiezaIt.repository;

import com.limpiezaIt.entity.Categoria;
import com.limpiezaIt.entity.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CategoriaRepositoryTest {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ProductoRepository productoRepository;

    @Test
    void testfindByNombreContainingIgnoreCase(){
        //Given
        Categoria categoria = Categoria.builder()
                .nombre("Test Cat")
                .descripcion("Categoria Test")
                .build();
        categoriaRepository.save(categoria);

        //When
        List<Categoria> resultado = categoriaRepository.findByNombreContainingIgnoreCase("test cat");

        //Then
        assertFalse(resultado.isEmpty());
        assertEquals("Test Cat", resultado.get(0).getNombre());

    }

    @Test
    void testContarProductosPorCategoria_Simple() {
        // Given - Una categoría con productos
        Categoria categoria = categoriaRepository.save(
                Categoria.builder().nombre("Test").descripcion("Test").build()
        );

        productoRepository.save(Producto.builder()
                .nombre("Producto 1").precio(100.0).stock(5).categoria(categoria).activo(true).build());

        productoRepository.save(Producto.builder()
                .nombre("Producto 2").precio(200.0).stock(3).categoria(categoria).activo(true).build());

        // When
        List<Object[]> resultado = categoriaRepository.contarProductosPorCategoria();

        // Then
        assertEquals(1, resultado.size());

        Object[] primeraFila = resultado.get(0);
        assertEquals("Test", primeraFila[0]);        // Nombre categoría
        assertEquals(2L, primeraFila[1]);           // Cantidad productos
    }



}
