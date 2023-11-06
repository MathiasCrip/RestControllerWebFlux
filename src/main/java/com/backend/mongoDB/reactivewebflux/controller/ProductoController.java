package com.backend.mongoDB.reactivewebflux.controller;

import com.backend.mongoDB.reactivewebflux.documents.Producto;
import com.backend.mongoDB.reactivewebflux.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    /*En este caso, solo implementamos la consulta de listar, quedan por hacer: Buscar por Id, Borrar, guardar y modificar. */
    @GetMapping({"", "/", "/listar"})
    public Flux<Producto> listar() {
        return productoService.listar();
    }
}
