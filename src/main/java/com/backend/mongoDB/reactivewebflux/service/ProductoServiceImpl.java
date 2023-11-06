package com.backend.mongoDB.reactivewebflux.service;

import com.backend.mongoDB.reactivewebflux.documents.Categoria;
import com.backend.mongoDB.reactivewebflux.documents.Producto;
import com.backend.mongoDB.reactivewebflux.repositories.CategoriaRepository;
import com.backend.mongoDB.reactivewebflux.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*Implementamos la interfaz ProductoService y ejecutamos las operaciones */
@Service
public class ProductoServiceImpl implements ProductoService {

    //Inyectamos ProductoRepository y CategoriaRepository, para utilizar los metodos de ReactiveMongoRepository
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /*-------------OPERACIONES CRUD DE PRODUCTOS---------------*/

    @Override
    public Flux<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Mono<Producto> crear(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Mono<Producto> buscar(Producto producto) {
        return productoRepository.findById(producto.getId());
    }

    @Override
    public Mono<Void> eliminarPorId(String id) {
        return productoRepository.deleteById(id);
    }

    /*-------------OPERACIONES CRUD DE CATEGORIAS---------------*/
    @Override
    public Mono<Categoria> buscarCategoria(Categoria categoria) {
        return categoriaRepository.findById(categoria.getId());
    }

    @Override
    public Flux<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Mono<Categoria> crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Mono<Void> eliminarCategoriaPorId(String id) {
        return categoriaRepository.deleteById(id);
    }
}
