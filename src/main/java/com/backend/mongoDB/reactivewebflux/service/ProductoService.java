package com.backend.mongoDB.reactivewebflux.service;

import com.backend.mongoDB.reactivewebflux.documents.Categoria;
import com.backend.mongoDB.reactivewebflux.documents.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*Modelo de servicio. En esta interfaz, creamos un contrato de las operaciones
que queremos ralizar en la implementación.
    Dentro de este Service, tenemos operaciones de CRUD tanto de productos, como de Categorias.
    Se implementaron las operaciones de categorias dentro de ProductoService, porque el producto engloba a la categoria,
    es decir, la categoria son parte del producto
*/
public interface ProductoService {

    //Flux-> Es similar a cuando implementabamos List<producto>. Pero con flux -> creamos una colección tipo reactivo
    //Mono-> Recibe 0 o 1 elemento reactivo
    Flux<Producto> listar();

    Flux<Categoria> listarCategorias();

    Mono<Producto> buscar(Producto producto);

    Mono<Categoria> buscarCategoria(Categoria categoria);

    Mono<Producto> crear(Producto producto);

    Mono<Categoria> crearCategoria(Categoria categoria);

    Mono<Void> eliminarPorId(String id);

    Mono<Void> eliminarCategoriaPorId(String id);
}
